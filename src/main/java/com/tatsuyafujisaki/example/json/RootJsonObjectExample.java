package com.tatsuyafujisaki.example.json;

import org.json.JSONException;
import org.json.JSONObject;

class RootJsonObjectExample {
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

        try {
            var rootJsonObject = createJsonObjectOrNull(jsonString);
            if (rootJsonObject == null) {
                return;
            }

            var jsonArray = rootJsonObject.optJSONArray("myRoot");
            if (jsonArray == null) {
                return;
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                var jsonObject = jsonArray.optJSONObject(i);
                if (jsonObject == null) {
                    continue;
                }

                var myArray = jsonObject.optJSONArray("myArray");
                var myBool = jsonObject.optBooleanObject("myBool", null);
                var myDouble = jsonObject.optDoubleObject("myDouble", null);
                var myInt = jsonObject.optIntegerObject("myInt", null);
                var myString = jsonObject.optString("myString", null);

                System.out.println("myArray: " + myArray);
                System.out.println("myBool: " + myBool);
                System.out.println("myDouble: " + myDouble);
                System.out.println("myInt: " + myInt);
                System.out.println("myString: " + myString);
                System.out.println("----------");
            }

        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }

    private static JSONObject createJsonObjectOrNull(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
