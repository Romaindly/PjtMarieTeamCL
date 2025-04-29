package fr.dly_vrn;

import java.util.Collection;

public class BateauVoyageur extends Bateau {
    private double vitesseBatVoy;           // Vitesse en noeuds
    private String imageBatVoy;             // Chemin de l'image, ex : "/images/luce_isle.jpg"
    private Collection<Equipement> lesEquipements; // Facultatif

    public BateauVoyageur(String idBat, String nom, double longueurBat, double largeurBat,
                          double vitesseBatVoy, String imageBatVoy, Collection<Equipement> lesEquipements) {
        super(idBat, nom, longueurBat, largeurBat);
        this.vitesseBatVoy = vitesseBatVoy;
        this.imageBatVoy = imageBatVoy;
        this.lesEquipements = lesEquipements;
    }

    public double getVitesseBatVoy() {
        return vitesseBatVoy;
    }

    public String getImageBatVoy() {
        return imageBatVoy;
    }

    public Collection<Equipement> getLesEquipements() {
        return lesEquipements;
    }

    // Setters
    public void setVitesseBatVoy(double vitesseBatVoy) {
        this.vitesseBatVoy = vitesseBatVoy;
    }

    public void setImageBatVoy(String imageBatVoy) {
        this.imageBatVoy = imageBatVoy;
    }

    public void setLesEquipements(Collection<Equipement> lesEquipements) {
        this.lesEquipements = lesEquipements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("Vitesse : ").append(String.format("%.2f", vitesseBatVoy)).append(" noeuds\n");
        sb.append("Equipements : ");
        if (lesEquipements != null && !lesEquipements.isEmpty()) {
            for (Equipement eq : lesEquipements) {
                sb.append(eq.toString()).append(" - ");
            }
        } else {
            sb.append("Aucun");
        }
        return sb.toString();
    }
}
