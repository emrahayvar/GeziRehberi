package com.example.bitirmeprojesi;

import java.io.Serializable;

public class ItemsModel implements Serializable {

    private String name;
    private String email;
    private int images;
    private int images2;
    private int images3;
    private int images4;
    private String name1;
    private String name2;
    private String name3;



    public ItemsModel(String name, String email,int images,int images2,int images3, int images4, String name1, String name2, String name3) {
        this.name = name;
        this.email = email;
        this.images = images;
        this.images2 =images2;
        this.images3 = images3;
        this.images4 = images4;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getImages2(){
        return images2;
    }

    public void setImages2(int images2){
        this.images2 = images2;
    }

    public int getImages3(){
        return images3;
    }

    public void setImages3(int images3){
        this.images3 = images3;
    }

    public int getImages4(){
        return images4;
    }

    public void setImages4(int images4){
        this.images4 = images4;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }






}
