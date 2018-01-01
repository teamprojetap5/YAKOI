package com.example.mbraconnier.yakoi;

/**
 * Created by mbraconnier on 15/12/2017.
 */

public class ActivityObject {
    private int Id;
    private String Titre;
    private String Logo;
    private String Image;
    private String DateAffichage;
    private String CapaciteMax;
    private String Theme;
    private String Environnement;
    private String Budget;
    private String Description;
    private String Telephone;
    private String Horaire;
    private String Site;
    private String Email;
    private String[] Accessibilite;
    private String[] Paiement;
    private int Numero;
    private String Rue;
    private int CodePostal;
    private String Ville;

    public ActivityObject() {
    }

    public ActivityObject(int id, String titre, String logo, String image, String dateAffichage, String capaciteMax, String theme, String environnement, String budget, String description, String telephone, String horaire, String site, String email, String[] accessibilites, String[] modesDePaiement, int numero, String rue, int codePostal, String ville) {
        Id = id;
        Titre = titre;
        Logo = logo;
        Image = image;
        DateAffichage = dateAffichage;
        CapaciteMax = capaciteMax;
        Theme = theme;
        Environnement = environnement;
        Budget = budget;
        Description = description;
        Telephone = telephone;
        Horaire = horaire;
        Site = site;
        Email = email;
        Accessibilite = accessibilites;
        Paiement = modesDePaiement;
        Numero = numero;
        Rue = rue;
        CodePostal = codePostal;
        Ville = ville;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDateAffichage() {
        return DateAffichage;
    }

    public void setDateAffichage(String dateAffichage) {
        DateAffichage = dateAffichage;
    }

    public String getCapaciteMax() {
        return CapaciteMax;
    }

    public void setCapaciteMax(String capaciteMax) {
        CapaciteMax = capaciteMax;
    }

    public String getTheme() {
        return Theme;
    }

    public String getHoraire() {
        return Horaire;
    }

    public void setHoraire(String horaire) {
        Horaire = horaire;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }

    public String getEnvironnement() {
        return Environnement;
    }

    public void setEnvironnement(String environnement) {
        Environnement = environnement;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getSite() {
        return Site;
    }

    public void setSite(String site) {
        Site = site;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String[] getAccessibilite() {
        return Accessibilite;
    }

    public void setAccessibilite(String[] accessibilite) {
        Accessibilite = accessibilite;
    }

    public String[] getPaiement() {
        return Paiement;
    }

    public void setPaiement(String[] paiement) {
        Paiement = paiement;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public String getRue() {
        return Rue;
    }

    public void setRue(String rue) {
        Rue = rue;
    }

    public int getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(int codePostal) {
        CodePostal = codePostal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }
}
