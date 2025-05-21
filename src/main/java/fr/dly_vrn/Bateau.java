package fr.dly_vrn;

/**
 * Représente un bateau avec des attributs de base tels que l'identifiant, le nom,
 * la longueur et la largeur.
 */
public class Bateau {
    private String idBat;
    private String nom;
    private double longueurBat;
    private double largeurBat;

    /**
     * Constructeur de la classe Bateau.
     *
     * @param idBat       identifiant du bateau
     * @param nom         nom du bateau
     * @param longueurBat longueur du bateau
     * @param largeurBat  largeur du bateau
     */
    public Bateau(String idBat, String nom, double longueurBat, double largeurBat) {
        this.idBat = idBat;
        this.nom = nom;
        this.longueurBat = longueurBat;
        this.largeurBat = largeurBat;
    }

    /**
     * Retourne l'identifiant du bateau.
     *
     * @return identifiant du bateau
     */
    public String getIdBat() {
        return idBat;
    }

    /**
     * Retourne le nom du bateau.
     *
     * @return nom du bateau
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la longueur du bateau.
     *
     * @return longueur du bateau
     */
    public double getLongueurBat() {
        return longueurBat;
    }

    /**
     * Retourne la largeur du bateau.
     *
     * @return largeur du bateau
     */
    public double getLargeurBat() {
        return largeurBat;
    }

    /**
     * Définit l'identifiant du bateau.
     *
     * @param idBat identifiant du bateau
     */
    public void setIdBat(String idBat) {
        this.idBat = idBat;
    }

    /**
     * Définit le nom du bateau.
     *
     * @param nom nom du bateau
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit la longueur du bateau.
     *
     * @param longueurBat longueur du bateau
     */
    public void setLongueurBat(double longueurBat) {
        this.longueurBat = longueurBat;
    }

    /**
     * Définit la largeur du bateau.
     *
     * @param largeurBat largeur du bateau
     */
    public void setLargeurBat(double largeurBat) {
        this.largeurBat = largeurBat;
    }

    /**
     * Retourne une représentation textuelle du bateau.
     *
     * @return chaîne contenant le nom, la longueur et la largeur du bateau
     */
    @Override
    public String toString() {
        return "Nom du bateau : " + nom + "\n" +
                "Longueur : " + String.format("%.2f", longueurBat) + " mètres\n" +
                "Largeur : " + String.format("%.2f", largeurBat) + " mètres";
    }
}

