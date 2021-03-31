package com.example.martstock;

public class Prices {
    String animalType, priceChange, pricePerKilo;

    public Prices(String animalType, String priceChange, String pricePerKilo) {
        this.animalType = animalType;
        this.priceChange = priceChange;
        this.pricePerKilo = pricePerKilo;
    }

    public Prices() {
    }

    public String getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(String pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
