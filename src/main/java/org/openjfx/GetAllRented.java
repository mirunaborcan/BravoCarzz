package org.openjfx;

public class GetAllRented {
    String idRented, renter, pickDate, dropDate;

    public GetAllRented(String idRented, String renter, String pickDate, String dropDate) {
        this.idRented = idRented;
        this.renter = renter;
        this.pickDate = pickDate;
        this.dropDate = dropDate;
    }

    public String getIdRented() {
        return idRented;
    }

    public String getRenter() {
        return renter;
    }

    public String getPickDate() {
        return pickDate;
    }

    public String getDropDate() {
        return dropDate;
    }

    public void setIdRented(String idRented) {
        this.idRented = idRented;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public void setPickDate(String pickDate) {
        this.pickDate = pickDate;
    }

    public void setDropDate(String dropDate) {
        this.dropDate = dropDate;
    }
}
