package com.cbcc.bizboot.enums;

public enum Gender {

    MALE(0),
    FEMALE(1);

    final int key;

    Gender(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }
}
