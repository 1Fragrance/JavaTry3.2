package com.company.Helpers;

public class ListItemParser {
    public static int getId (String str) {
        String[] strArr = str.split(", ");
        return Integer.parseInt(strArr[0]);
    }



}
