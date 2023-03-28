// GET
// 
//     /
//     /login
//     /profile
//     /user
//     /user/[id]
//     /dashboard
//     /admin
//     /entity
//     /entity/[id]
//     /entity/[id]/feature
//     /entity/[id]/feature/[key]
//     /entity/feature/[key] 
//     /feature
//     /feature/[key]
//     /search?key=[key]&entity=[id]
// 
// POST, PUT, PATCH, DELETE
// 
//     /user/[id]
// 
//     /entity/[id]
//     /entity/[id]/feature
//     /entity/[id]/feature/[key]
//     /entity/feature/[key]
// 
//     /feature
//     /feature/[id]
// 
//     /search?key=[key]&entity=[id]
// 
// HEAD
//
//
// entity = {
//     id: 0,
//     features: [...]
// }
//
// feature = {
//     id: 0,
//     key: "[key]",
//     data: "[data]",
//     type: "[type]"
// }

// TODO: HAL relations

package com.iloveyou;

public class Mock {
    private static String makeEntity(String id, String... features) {
        String entity;

        entity = "{" +
                 "\"id\": " + id + ", " +
                 "\"features\":[";

        for(int i = 0; i < features.length; i++) {
            entity += features[i];

            if(i < features.length - 1)
                entity += ", ";

            else
                entity += "";
        }
        
        entity += "]}";

        return entity;
    }

    private static String makeFeature(String id, String key, String data, String type) {
        return "{" +
               "\"id\": " + id + ", " +
               "\"key\": \"" + key + "\", " +
               "\"data\": \"" + data + "\", " + 
               "\"type\": \"" + type + "\"" +
               "}";
    }

    // public static String entity() {}

    public static String entity(String id) {
        return makeEntity(
            id,
            makeFeature("0", "key0", "data0", "text"),
            makeFeature("1", "key1", "0.12345", "number"),
            makeFeature("2", "key2", "true", "boolean"),
            makeFeature("3", "key3", null, "text")
        );
    }

    public static String entityFeature(String id) {
        return makeFeature(id, "key" + id, "data" + id, "text");
    }

    public static String entityFeature(String id, String key) {
        return makeFeature(id, key, "data" + id, "text");
    }

    public static String featureEntity(String key) {
        return "[\n" +
               makeEntity(
                   "0",
                   makeFeature("0", "key0", "data0", "text"),
                   makeFeature("1", "key1", "data1", "text"),
                   makeFeature("2", "key2", "data2", "text")
               ) + ",\n" +
               makeEntity(
                "1",
                makeFeature("3", "key0", "data0", "text"),
                makeFeature("4", "key3", "data3", "text"),
                makeFeature("5", "key4", "data4", "text"),
                makeFeature("6", "key5", "data5", "text")
            ) + ",\n" + "]";
    }

    // TODO: implement
    // public static String feature() {}

    public static String feature(String id) {
        return makeFeature(id, "key", "", "text");
    }
}

