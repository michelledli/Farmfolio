package com.iloveyou.domain;

public class Feature {
    public enum FeatureType { TEXT, NUMBER, BOOL };

    String key;
    String data;  
    String label;
    int id; // feature id
    FeatureType type;

    public Feature(int id,String key,String label, FeatureType type, String data) {
        this.id = id;
        this.key = key;
        this.data = data;
        this.label = label;
        this.type = type;
    }

    public String getKey() {
        return this.key;
    }

    public String getdata() {
        return this.data;
    }

    public FeatureType getType() {
        return this.type;
    }

    public int getId() {
        return this.id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setdata(String data) {
        this.data = data;
    }

    public void setType(FeatureType type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

}
