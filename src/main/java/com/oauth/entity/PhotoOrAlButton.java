package com.oauth.entity;

import java.util.ArrayList;
import java.util.List;

public class PhotoOrAlButton extends AbstractButton {
    private String type = "";
    private String key;
    private List<AbstractButton> subButton = new ArrayList<AbstractButton>();

    public PhotoOrAlButton(String name,String key) {
        super(name);
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<AbstractButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<AbstractButton> subButton) {
        this.subButton = subButton;
    }
}
