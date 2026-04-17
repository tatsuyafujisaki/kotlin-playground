package com.tatsuyafujisaki.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class IoUtils {
    private String readTextFile(String fileName) {
        try (var inputStream = getClass().getResourceAsStream(fileName)) {
            return inputStream != null ?
                    new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim() :
                    "";
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "";
        }
    }
}
