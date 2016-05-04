package squidteam.main;

import squidteam.ihm.Serveur;

public class Main {

  public static void main(String[] args) {

    try {
      Serveur.lancerServeur();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
