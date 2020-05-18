package com.dkatails.data;

/**
 * State used to store each comparison results.
 */
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
