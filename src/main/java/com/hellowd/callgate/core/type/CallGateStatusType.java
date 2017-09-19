package com.hellowd.callgate.core.type;

public enum CallGateStatusType {
    SUCCESS("성공"),
    FAIL("실패"),
    NONE("없음");

    private String text;

    CallGateStatusType(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
