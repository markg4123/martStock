package com.example.martstock;

public class Report {
    String weight, price, gender, breed, dob, date;

    public  Report(){

    }

    public Report(String weight, String price, String gender, String breed, String dob, String date) {
        this.weight = weight;
        this.price = price;
        this.gender = gender;
        this.breed = breed;
        this.dob = dob;
        this.date = date;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Report{" +
                "weight='" + weight + '\'' +
                ", price='" + price + '\'' +
                ", gender='" + gender + '\'' +
                ", breed='" + breed + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }

}
