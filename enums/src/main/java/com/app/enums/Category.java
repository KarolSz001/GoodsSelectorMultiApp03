package com.app.enums;

public enum Category {
    FOOD, BOOK, ELECTRONIC;

    Category() {
    }

    public static Category convertFromNumber(int number){
        if (number < 0 || number > 2){
            return Category.values()[0];
        }
        return Category.values()[number];
    }
}
