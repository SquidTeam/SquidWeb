package Squidteam.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

  public static void main(String[] args) {
    /* Chargement du driver JDBC pour MySQL */

    try {

      Class.forName("com.mysql.jdbc.Driver");

    } catch (ClassNotFoundException e) {

      System.out.println("Driver manquant !");

    }

    String url = "jdbc:mysql://vps254243.ovh.net:3306/SquidDB";

    String utilisateur = "Squid";

    String motDePasse = "squidpw";

    Connection connexion = null;
    String a = "a";
    String b = "b";

    if (a == b) {
      System.out.println(a);
      System.out.println(b);
    }

    try {

      connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

      System.out.println("Connexion ok !");
      Statement statement = connexion.createStatement();
      int statut = statement.executeUpdate("INSERT INTO users(firstname) VALUES ('Benjamin')");
      System.out.println(statut);
    } catch (SQLException e) {

      System.out.println("Serveur injoignable");

    } finally {

      if (connexion != null)

        try {

          /* Fermeture de la connexion */

          connexion.close();
          System.out.println("Test");
          System.out.println("Test");

          System.out.println("Test");

          System.out.println("Test");

          System.out.println("Test");

          System.out.println("Test");

          System.out.println("Test");
          if (1 == 2) {
            System.out.println("Test");

            System.out.println("Test");

            System.out.println("Test");

            System.out.println("Test");
            System.out.println("Test");


          }
        } catch (SQLException ignore) {

          /*
           * Si une erreur survient lors de la fermeture, il suffit de l'ignorer.
           */

        }

    }
  }
}
