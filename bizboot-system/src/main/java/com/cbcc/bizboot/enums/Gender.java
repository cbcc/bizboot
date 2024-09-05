package com.cbcc.bizboot.enums;

public enum Gender {

    MALE(1),
    FEMALE(2);

    final int key;

    Gender(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }
}
