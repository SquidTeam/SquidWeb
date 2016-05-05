package squidteam.ihm;

import squidteam.biz.dto.UtilisateurDto;
import squidteam.biz.ucc.UtilisateurUcc;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainServlet extends HttpServlet {

  private static String SECRET = "give me all the bacon and eggs you have";
  Genson genson = new GensonBuilder().useDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
      .useIndentation(true).useConstructorWithArguments(true).create();


  public MainServlet() {

  }

  /**
   * Methode gérant les requêtes http Post
   * 
   * @param req
   * @param resp
   * 
   * 
   */

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String path = req.getPathInfo();
    System.out.println("longpath=" + path);
    String[] paths = path.split("/");
    System.out.println(paths.toString());

    switch (paths[1]) {
      case "inscription": {
        switch (paths[2]) {
          case "signup": {
            HttpSession sess = req.getSession();
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String email = req.getParameter("email");
            String pseudo = req.getParameter("pseudo");
            String pwd = req.getParameter("pwd");
            String confirmation = req.getParameter("confirmation");

            final UtilisateurDto user = new UtilisateurDto();
            user.setPseudo(pseudo);
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setEmail(email);
            user.setPassword(pwd);
            user.setConfirmation(confirmation);

            System.out.println(user);
            System.out.println("Ici");
            UtilisateurUcc uucc = new UtilisateurUcc();
            if (uucc.inscrire(user)) {
              Map<String, Object> person = new HashMap<String, Object>() {
                {
                  put("login", user.getPseudo());
                  put("email", user.getEmail());
                  put("name", user.getNom());
                  put("prenom", user.getPrenom());
                }
              };

              String token = new JWTSigner(SECRET).sign(person);

              Cookie cookie = new Cookie("token", token); // add some description to be viewed in
              cookie.setComment("SetCookie Counter"); // setting max age to
              cookie.setMaxAge(24 * 60 * 60); // set path to make it accessible to only
              cookie.setPath("/squidteam");

              String json = genson.serialize(person);
              sess.setAttribute("user", json);
              resp.setContentType("text/json");
              resp.setContentLength(json.getBytes("UTF-8").length);
              resp.setCharacterEncoding("UTF-8");
              resp.setStatus(200);
              resp.getOutputStream().write(json.getBytes("UTF-8"));
              break;
            }

          }
            break;
        }

      }
      case "connexion": {
        switch (paths[2]) {
          case "verification": {
            Cookie[] cookies = req.getCookies();
            if (req.getSession().getAttribute("user") != null) {
              String json = (String) req.getSession().getAttribute("user");
              resp.setContentType("text/json");
              resp.setContentLength(json.getBytes("UTF-8").length);
              resp.setCharacterEncoding("UTF-8");
              resp.setStatus(200);
              resp.getOutputStream().write(json.getBytes("UTF-8"));
            }
            if (cookies != null) {
              for (Cookie c : cookies) {
                System.out.println(c.getName());
                if (c.getName().equals("token")) {
                  try {
                    final Map<String, Object> decodedPayload =
                        new JWTVerifier(SECRET).verify(c.getValue());
                    HashMap<String, Object> person = new HashMap<String, Object>() {
                      {
                        put("login", decodedPayload.get("username"));
                        put("email", decodedPayload.get("email"));
                        put("name", decodedPayload.get("nom"));
                        put("prenom", decodedPayload.get("prenom"));
                        put("estProf", decodedPayload.get("estProf"));
                      }
                    };
                    String json = genson.serialize(person);
                    if (req.getSession().getAttribute("user") == null) {
                      req.getSession().setAttribute("user", json);
                    }
                    resp.setContentType("text/json");
                    resp.setContentLength(json.getBytes("UTF-8").length);
                    resp.setCharacterEncoding("UTF-8");
                    resp.setStatus(200);
                    resp.getOutputStream().write(json.getBytes("UTF-8"));
                  } catch (SignatureException signatureException) {
                    System.err.println("Invalid signature!");
                  } catch (IllegalStateException illegalStateException) {
                    System.err.println("Invalid Token! " + illegalStateException);
                  } catch (InvalidKeyException ex) {
                    System.err.println("Invalid Key! " + ex);
                  } catch (NoSuchAlgorithmException ex) {
                    System.err.println("Invalid Algorithm! " + ex);
                  } catch (IOException ex) {
                    ex.printStackTrace();
                  } catch (JWTVerifyException ex) {
                    System.err.println("JWT Verify Exception! " + ex);
                  }

                }
              }
            }
            resp.setStatus(404);
            break;
          }
        }
        break;
      }

    }



  }
}
