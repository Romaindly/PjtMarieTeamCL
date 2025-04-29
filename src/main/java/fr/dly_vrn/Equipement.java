package fr.dly_vrn;

public class Equipement {
    private String idEquip;
    private String libEquip;

    public Equipement(String idEquip, String libEquip) {
        this.idEquip = idEquip;
        this.libEquip = libEquip;
    }

    public String getIdEquip() {
        return idEquip;
    }

    public String getLibEquip() {
        return libEquip;
    }

    @Override
    public String toString() {
        return libEquip;
    }
}
