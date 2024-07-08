package com.trabalho.ufc.utils;

import java.util.Collection;

public class StringUtil {

    private StringUtil() {}

    public static boolean isNullOrEmpty(Object object) {
        return object == null
                || (object.getClass() == String.class && object.toString().isEmpty())
                || (object instanceof Collection && ((Collection) object).isEmpty());
    }
}
