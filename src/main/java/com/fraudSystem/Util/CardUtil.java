package com.fraudSystem.Util;

public class CardUtil {

    public static String maskCard(String cardNo){

        if (cardNo == null || cardNo.length()<4){
            return "Invalid Card";
        }

        String substring = cardNo.substring(cardNo.length() - 4);

        return "*** ****** ***"+substring;

    }
}
