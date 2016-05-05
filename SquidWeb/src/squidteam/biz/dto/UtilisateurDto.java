package squidteam.biz.dto;

public class UtilisateurDto {



  private String nom;
  private String prenom;
  private String pseudo;
  private String email;
  private String password;
  private String confirmation;


  public UtilisateurDto() {

  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getPseudo() {
    return pseudo;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmation() {
    return confirmation;
  }

  public void setConfirmation(String confirmation) {
    this.confirmation = confirmation;
  }

  @Override
  public String toString() {
    return "UtilisateurDto [nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", email="
        + email + ", password=" + password + ", confirmation=" + confirmation + "]";
  }

}
