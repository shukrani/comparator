package com.dkatails.data;

public enum State {
    ERROR("error"), NOT_EQUEAL("not equals"), EQUAL("equals");

    private String value;

    private State(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
