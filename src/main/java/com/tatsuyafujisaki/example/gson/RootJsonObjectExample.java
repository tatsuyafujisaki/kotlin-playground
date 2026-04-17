package com.tatsuyafujisaki.example.gson;

import com.google.gson.Gson;

import java.util.List;

class MyRootObject {
    private List<MyData> myRoot;

    public List<MyData> getMyRoot() {
        return myRoot;
    }
}

public class RootJsonObjectExample {
    public static void main(String[] args) {
        var jsonString = """
                {
                    "myRoot": [
                        {
                            "myArray": [],
                            "myBool": true,
                            "myDouble": 1.1,
                            "myInt": 1,
                            "myString": "🍎"
                        },
                        {
                            "myArray": null,
                            "myBool": null,
                            "myDouble": null,
                            "myInt": null,
                            "myString": null
                        },
                        {}
                    ]
                }
                """;

        var gson = new Gson();
        
        try {
            var rootObject = gson.fromJson(jsonString, MyRootObject.class);
            System.out.println(gson.toJson(rootObject));
            System.out.println("--");
            for (var data : rootObject.getMyRoot()) {
                System.out.println(gson.toJson(data));
                System.out.println("--");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
