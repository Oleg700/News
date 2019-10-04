package com.epam.news;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String text    = "Thitext1";


        String patternString = "^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]+$";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(text);
        System.out.println(matcher.matches());
    }
}
