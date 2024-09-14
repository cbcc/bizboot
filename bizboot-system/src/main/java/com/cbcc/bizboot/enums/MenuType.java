package com.cbcc.bizboot.enums;

public enum MenuType {

    MENU(0),
    IFRAME(1),
    EXTERNAL(2),
    BUTTON(3);

    final int key;

    MenuType(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }
}
