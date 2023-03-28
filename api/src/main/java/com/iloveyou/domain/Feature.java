package com.iloveyou.domain;

public class Feature {
    public enum FeatureType { TEXT, NUMBER, BOOL };

    String key;
    String value;
    FeatureType type;

    public Feature(String key, String value, FeatureType type) {}
}
