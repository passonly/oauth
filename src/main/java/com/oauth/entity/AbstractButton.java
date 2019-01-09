package com.oauth.entity;

public class AbstractButton {

    public AbstractButton(String name) {
        this.name = name;
    }

    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
