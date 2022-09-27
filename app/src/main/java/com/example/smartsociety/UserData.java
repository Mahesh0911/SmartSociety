package com.example.smartsociety;

public class UserData {
    public String name;
    public String mobile_no;
    public String email;
    public String flat_no;
    public String relation_status;

//    map.put("name", nam);
//        map.put("mobile_no",mno);
//        map.put("email", eml);
//        map.put("flat_no", flat);
//        map.put("relation_status",relStatus);

    public UserData(String eml,String nam, String mno, String flat,String relStatus){
        this.name=nam;
        this.mobile_no=mno;
        this.email=eml;
        this.flat_no=flat;
        this.relation_status=relStatus;
    }
    public String getName(){
        return name;
    }
    public String getMobile_no(){
        return mobile_no;
    }
    public String getEmail(){
        return email;
    }
    public String getFlat_no(){
        return flat_no;
    }
    public String getRelation_status(){
        return relation_status;
    }

}
