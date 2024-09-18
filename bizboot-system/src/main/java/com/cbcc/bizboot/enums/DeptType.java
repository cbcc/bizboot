package com.cbcc.bizboot.enums;

public enum DeptType {

    HEAD(0),
    BRANCH(1),
    DEPT(2);

    final int key;

    DeptType(int key) {
        this.key = key;
    }

    public int key() {
        return key;
    }
}
