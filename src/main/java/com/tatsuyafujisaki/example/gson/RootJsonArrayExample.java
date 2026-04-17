package com.tatsuyafujisaki.example.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class RootJsonArrayExample {
    public static void main(String[] args) {
        var jsonString = """
                [
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
                """;

        var gson = new Gson();
        List<MyData> dataList = gson.fromJson(jsonString, new TypeToken<List<MyData>>() {
        }.getType());

        System.out.println(gson.toJson(dataList));
        for (var data : dataList) {
            System.out.println(gson.toJson(data));
            System.out.println("--");
        }
    }
}
