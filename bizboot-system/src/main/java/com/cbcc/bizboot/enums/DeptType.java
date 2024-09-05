package com.cbcc.bizboot.enums;

public enum DeptType {

    HEAD(1),
    BRANCH(2),
    DEPT(3);

    final int key;

    DeptType(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }
}
