import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Hello {
  public static void main(String[] args) {

    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Driver postgresql manquant !");
      System.exit(1);
    }

    System.out.println("Ok Driver");

    String url = "jdbc:postgresql://localhost/SquidDB";

    String utilisateur = "postgres";

    String motDePasse = "00tintin";
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, utilisateur, motDePasse);
    } catch (SQLException e) {
      System.out.println("Impossible de joindre le server !");
      System.exit(1);
    }

    try {
      Statement s = conn.createStatement();
      s.executeUpdate("INSERT INTO squid.utilisateurs(nom) VALUES('Test');");
    } catch (SQLException se) {
      System.out.println("Erreur lors de l’insertion!");
      se.printStackTrace();
      System.exit(1);
    }

  }
}


