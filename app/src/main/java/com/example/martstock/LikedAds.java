package com.example.martstock;

public class LikedAds {
    String adTitle;
    int price;
    String mart,breed,age,description,url,date,id,key;


    public LikedAds() {

    }

    LikedAds(String adTitle, int price, String mart, String breed, String age, String description, String url, String date, String id,String key){
        this.adTitle = adTitle;
        this.price = price;
        this.mart = mart;
        this.breed = breed;
        this.age = age;
        this.description = description;
        this.url = url;
        this.date = date;
        this.id = id;
        this.key =key;


    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getMart() {
        return mart;
    }

    public void setMart(String mart) {
        this.mart = mart;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "adTitle='" + adTitle + '\'' +
                ", price=" + price +
                ", mart='" + mart + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}

