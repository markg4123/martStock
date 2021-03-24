package com.example.martstock;

public class Ad {
    String adTitle;
    int price;
    String section;
    String mart;
    String breed;
    String age;
    String description;
    String id;
    String url;
    String date;
    String key;
    String subsection;

    public Ad() {
    }

    Ad(String adTitle, int price, String section, String mart, String breed, String age,
       String description, String id, String url, String date,String key, String subsection){
            this.adTitle = adTitle;
            this.price = price;
            this.section = section;
            this.mart = mart;
            this.breed = breed;
            this.age = age;
            this.description = description;
            this.id = id;
            this.url = url;
            this.date = date;
            this.key = key;
            this.subsection = subsection;



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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "adTitle='" + adTitle + '\'' +
                ", price=" + price +
                ", section='" + section + '\'' +
                ", mart='" + mart + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}

