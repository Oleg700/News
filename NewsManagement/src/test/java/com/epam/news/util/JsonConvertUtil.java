package com.epam.news.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConvertUtil {

    public static String transformToJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
