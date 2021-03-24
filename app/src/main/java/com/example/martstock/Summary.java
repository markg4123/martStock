package com.example.martstock;

public class Summary {
    String bullockAvgPrice,heiferAvgPrice,cowAvgPrice,calfAvgPrice;


    public Summary(){

    }
    public Summary(String bullockAvgPrice, String heiferAvgPrice, String cowAvgPrice, String calfAvgPrice){
        this.bullockAvgPrice = bullockAvgPrice;
        this.heiferAvgPrice = heiferAvgPrice;
        this.cowAvgPrice = cowAvgPrice;
        this.calfAvgPrice = calfAvgPrice;
    }

    public String getCalfAvgPrice() {
        return calfAvgPrice;
    }

    public void setCalfAvgPrice(String calfAvgPrice) {
        this.calfAvgPrice = calfAvgPrice;
    }

    public String getCowAvgPrice() {
        return cowAvgPrice;
    }

    public void setCowAvgPrice(String cowAvgPrice) {
        this.cowAvgPrice = cowAvgPrice;
    }

    public String getHeiferAvgPrice() {
        return heiferAvgPrice;
    }

    public void setHeiferAvgPrice(String heiferAvgPrice) {
        this.heiferAvgPrice = heiferAvgPrice;
    }

    public String getBullockAvgPrice() {
        return bullockAvgPrice;
    }

    public void setBullockAvgPrice(String bullockAvgPrice) {
        this.bullockAvgPrice = bullockAvgPrice;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "bullockAvgPrice='" + bullockAvgPrice + '\'' +
                ", heiferAvgPrice='" + heiferAvgPrice + '\'' +
                ", cowAvgPrice='" + cowAvgPrice + '\'' +
                ", calfAvgPrice='" + calfAvgPrice + '\'' +
                '}';
    }
}
