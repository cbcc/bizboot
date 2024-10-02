package com.cbcc.bizboot.enums;

public enum NotificationType {

    SYSTEM(0),
    MESSAGE(1);

    final int key;

    NotificationType(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }
}
