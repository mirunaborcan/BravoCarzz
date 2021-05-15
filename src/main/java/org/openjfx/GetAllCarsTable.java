package org.openjfx;

public class GetAllCarsTable {

    String id, brand, model, year, regNr, details, engPwr, aliment, gearbox;

    public GetAllCarsTable(String id, String brand, String model, String year, String regNr, String details, String engPwr, String aliment, String gearbox) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.regNr = regNr;
        this.details = details;
        this.engPwr = engPwr;
        this.aliment = aliment;
        this.gearbox = gearbox;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getRegNr() {
        return regNr;
    }

    public String getDetails() {
        return details;
    }

    public String getEngPwr() {
        return engPwr;
    }

    public String getAliment() {
        return aliment;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setEngPwr(String engPwr) {
        this.engPwr = engPwr;
    }

    public void setAliment(String aliment) {
        this.aliment = aliment;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }
}
