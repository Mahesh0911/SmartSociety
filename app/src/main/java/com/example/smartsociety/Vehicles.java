package com.example.smartsociety;

public class Vehicles {
    public  String name;
    public  String num;

    public Vehicles(){}
    public Vehicles(String mobile_no,String name){
        this.num=mobile_no;
        this.name=name;
    }

//    public String getEmail() {
//        return name;
//    }
    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}
