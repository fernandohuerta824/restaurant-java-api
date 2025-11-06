package com.fernando.sprinboot.restaurant.proyect.restaurant.helpers;

public class CorrectPage {
    private CorrectPage() {
        // Private constructor to prevent instantiation
    }

   public static int setCorrectPage(long numItems, int itemsPerPage, int page) {
        if (page < 1) {
            page = 1;
        }

        int maxPage = (int) Math.ceil((double) numItems / itemsPerPage);
        if( maxPage == 0) {
            maxPage = 1;
        }

        if (maxPage < page) {
            page = maxPage;
        }

        return page;
    }


}
