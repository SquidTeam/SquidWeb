package squidteam.ihm;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;

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



}

