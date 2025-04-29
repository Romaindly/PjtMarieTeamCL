package fr.dly_vrn;

public class Bateau {
    // Attributs de base pour le bateau
    private String idBat;
    private String nom;
    private double longueurBat;
    private double largeurBat;

    public Bateau(String idBat, String nom, double longueurBat, double largeurBat) {
        this.idBat = idBat;
        this.nom = nom;
        this.longueurBat = longueurBat;
        this.largeurBat = largeurBat;
    }

    // Getters
    public String getIdBat() {
        return idBat;
    }

    public String getNom() {
        return nom;
    }

    public double getLongueurBat() {
        return longueurBat;
    }

    public double getLargeurBat() {
        return largeurBat;
    }

    // Setters
    public void setIdBat(String idBat) {
        this.idBat = idBat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLongueurBat(double longueurBat) {
        this.longueurBat = longueurBat;
    }

    public void setLargeurBat(double largeurBat) {
        this.largeurBat = largeurBat;
    }

    @Override
    public String toString() {
        return "Nom du bateau : " + nom + "\n" +
                "Longueur : " + String.format("%.2f", longueurBat) + " mètres\n" +
                "Largeur : " + String.format("%.2f", largeurBat) + " mètres";
    }
}
