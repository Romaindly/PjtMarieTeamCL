package fr.dly_vrn;

import java.util.Collection;

/**
 * Représente un bateau destiné au transport de voyageurs,
 * avec des informations supplémentaires telles que la vitesse,
 * l'image et les équipements associés.
 */
public class BateauVoyageur extends Bateau {
    private double vitesseBatVoy;           // Vitesse en noeuds
    private String imageBatVoy;             // Chemin de l'image, ex : "/images/luce_isle.jpg"
    private Collection<Equipement> lesEquipements;

    /**
     * Constructeur de la classe BateauVoyageur.
     *
     * @param idBat          identifiant du bateau
     * @param nom            nom du bateau
     * @param longueurBat    longueur du bateau
     * @param largeurBat     largeur du bateau
     * @param vitesseBatVoy  vitesse du bateau en noeuds
     * @param imageBatVoy    chemin de l'image du bateau
     * @param lesEquipements collection d'équipements associés au bateau
     */
    public BateauVoyageur(String idBat, String nom, double longueurBat, double largeurBat,
                          double vitesseBatVoy, String imageBatVoy, Collection<Equipement> lesEquipements) {
        super(idBat, nom, longueurBat, largeurBat);
        this.vitesseBatVoy = vitesseBatVoy;
        this.imageBatVoy = imageBatVoy;
        this.lesEquipements = lesEquipements;
    }

    /**
     * Retourne la vitesse du bateau.
     *
     * @return vitesse en noeuds
     */
    public double getVitesseBatVoy() {
        return vitesseBatVoy;
    }

    /**
     * Définit la vitesse du bateau.
     *
     * @param vitesseBatVoy vitesse en noeuds
     */
    public void setVitesseBatVoy(double vitesseBatVoy) {
        this.vitesseBatVoy = vitesseBatVoy;
    }

    /**
     * Retourne le chemin de l'image du bateau.
     *
     * @return chemin de l'image
     */
    public String getImageBatVoy() {
        return imageBatVoy;
    }

    /**
     * Définit le chemin de l'image du bateau.
     *
     * @param imageBatVoy chemin de l'image
     */
    public void setImageBatVoy(String imageBatVoy) {
        this.imageBatVoy = imageBatVoy;
    }

    /**
     * Retourne la collection d'équipements associés.
     *
     * @return équipements du bateau
     */
    public Collection<Equipement> getLesEquipements() {
        return lesEquipements;
    }

    /**
     * Définit la collection d'équipements du bateau.
     *
     * @param lesEquipements collection d'équipements
     */
    public void setLesEquipements(Collection<Equipement> lesEquipements) {
        this.lesEquipements = lesEquipements;
    }

    /**
     * Retourne une représentation textuelle du bateau voyageur.
     *
     * @return chaîne contenant les informations du bateau
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Vitesse : " + String.format("%.2f", vitesseBatVoy) + " noeuds\n";
    }
}
