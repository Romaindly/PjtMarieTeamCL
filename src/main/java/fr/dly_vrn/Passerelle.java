package fr.dly_vrn;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe utilitaire fournissant des méthodes d'accès aux données,
 * notamment le chargement des équipements et des bateaux voyageurs
 * depuis une base de données.
 */
public class Passerelle {

    /**
     * Charge la collection des équipements associés à un bateau.
     *
     * @param idBateau l'identifiant du bateau
     * @return une collection d'objets Equipement associés au bateau
     */

    public static Collection<Equipement> chargerLesEquipements(String idBateau) {
        Collection<Equipement> equipements = new ArrayList<>();
        // Exemple de requête SQL, adaptez-la à votre schéma
        String sql = "SELECT e.id AS idEquip, e.nom AS libEquip " +
                "FROM bateau_equipement be " +
                "JOIN equipement e ON be.id_equipement = e.id " +
                "WHERE be.id_bateau = " + idBateau;

        JeuEnregistrement jeu = new JeuEnregistrement(sql);
        // Parcourir les enregistrements jusqu'à ce qu'on atteigne la fin
        while (!jeu.fin()) {
            Object idEquipObj = jeu.getValeur("idEquip");
            Object libEquipObj = jeu.getValeur("libEquip");
            if (idEquipObj != null && libEquipObj != null) {
                String idEquip = String.valueOf(idEquipObj);
                String libEquip = libEquipObj.toString();
                equipements.add(new Equipement(idEquip, libEquip));
            }
            jeu.suivant();// Avancer au prochain enregistrement
        }
        jeu.fermer();
        return equipements;
    }

    /**
     * Charge la collection des bateaux voyageurs depuis la base de données.
     *
     * @return une collection d'objets BateauVoyageur
     */

    public static Collection<BateauVoyageur> chargerLesBateauxVoyageurs() {
        Collection<BateauVoyageur> bateaux = new ArrayList<>();
        // Exemple de requête SQL, adaptez-la à votre table "bateau"
        String sql = "SELECT id, nom, longueur, largeur, vitesse, url_image FROM bateau";
        JeuEnregistrement jeu = new JeuEnregistrement(sql);

        while (!jeu.fin()) {
            Object idObj = jeu.getValeur("id");
            Object nomObj = jeu.getValeur("nom");
            Object longueurObj = jeu.getValeur("longueur");
            Object largeurObj = jeu.getValeur("largeur");
            Object vitesseObj = jeu.getValeur("vitesse");
            Object imageObj = jeu.getValeur("url_image");

            if (idObj != null && nomObj != null && longueurObj != null &&
                    largeurObj != null && vitesseObj != null) {

                String idBat = String.valueOf(idObj);
                String nomBat = nomObj.toString();
                double longueur = Double.parseDouble(longueurObj.toString());
                double largeur = Double.parseDouble(largeurObj.toString());
                double vitesse = Double.parseDouble(vitesseObj.toString());
                String image = (imageObj != null) ? imageObj.toString() : "";

                // Charger les équipements associés à ce bateau
                Collection<Equipement> equipements = chargerLesEquipements(idBat);

                // Créer l'objet BateauVoyageur
                BateauVoyageur bv = new BateauVoyageur(idBat, nomBat, longueur, largeur, vitesse, image, equipements);
                bateaux.add(bv);
            }
            jeu.suivant();// Passer à l'enregistrement suivant
        }
        jeu.fermer();
        return bateaux;
    }
}

