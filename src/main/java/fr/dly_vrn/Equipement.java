package fr.dly_vrn;

/**
 * Représente un équipement pouvant être associé à un bateau.
 */
public class Equipement {
    private String idEquip;
    private String libEquip;

    /**
     * Constructeur de la classe Equipement.
     *
     * @param idEquip identifiant de l'équipement
     * @param libEquip libellé de l'équipement
     */
    public Equipement(String idEquip, String libEquip) {
        this.idEquip = idEquip;
        this.libEquip = libEquip;
    }

    /**
     * Retourne l'identifiant de l'équipement.
     *
     * @return identifiant de l'équipement
     */
    public String getIdEquip() {
        return idEquip;
    }

    /**
     * Retourne le libellé de l'équipement.
     *
     * @return libellé de l'équipement
     */
    public String getLibEquip() {
        return libEquip;
    }

    /**
     * Retourne une représentation textuelle de l'équipement.
     *
     * @return libellé de l'équipement
     */
    @Override
    public String toString() {
        return libEquip;
    }
}

