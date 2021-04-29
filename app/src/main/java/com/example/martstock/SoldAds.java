package com.example.martstock;

public class SoldAds {
    String title,date,mart,id;
    int numSold, price, askingPrice;
    double avgWeight, pricePerKilo, difference;


    public SoldAds() {
    }

    public SoldAds(String title, String date, String mart, String id, int numSold, int price, double avgWeight, double pricePerKilo, int askingPrice, double difference) {
        this.title = title;
        this.numSold = numSold;
        this.price = price;
        this.avgWeight = avgWeight;
        this.pricePerKilo = pricePerKilo;
        this.date = date;
        this.mart=mart;
        this.id =id;
        this.askingPrice = askingPrice;
        this.difference = difference;
    }

    public double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMart() {
        return mart;
    }

    public void setMart(String mart) {
        this.mart = mart;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public double getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(double pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public double getAvgWeight() {
        return avgWeight;
    }

    public void setAvgWeight(double avgWeight) {
        this.avgWeight = avgWeight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumSold() {
        return numSold;
    }

    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(int askingPrice) {
        this.askingPrice = askingPrice;
    }
}
