package com.fernando.sprinboot.restaurant.proyect.restaurant.constants;

public enum PaginationData {
    SHORT_PAGE_SIZE(10),
    DEFAULT_PAGE_SIZE(20),
    LARGE_PAGE_SIZE(50);

    private final int value;

    PaginationData(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
