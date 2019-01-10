package com.oauth.entity;

import java.util.ArrayList;
import java.util.List;

public class SubButton extends AbstractButton{

    private List<AbstractButton> subButton = new ArrayList();

    public SubButton(String name) {
        super(name);
    }

    public List<AbstractButton> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<AbstractButton> subButton) {
        this.subButton = subButton;
    }
}
