package com.apptimized.tools.randomizer.Utils;

public class ActionUtils {

    public static String prepareQuery(String query) {
        query = query.toLowerCase().replace(" ", "+").
                replace("=", "%3D").
                replace("~", "%7E").
                replace("}", "%7D").
                replace("{", "%7B").
                replace("(", "%28").
                replace(")", "%29").
                replace("*", "%2A").
                replace("!", "%21").
                replace(",", "%2C").
                replace("\"", "%22");
        return query;
    }

}
