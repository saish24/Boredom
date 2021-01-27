package com.example.bored;

import com.google.gson.annotations.SerializedName;

public class BorAct {
    @SerializedName("activity")
    private String Act = "";
    private String type = "";
    private float price = 0.0f;
    private float accessibility = 0.0f;

    public BorAct(String act, String type, float price, float accessibility) {
        Act = act;
        this.type = type;
        this.price = price;
        this.accessibility = accessibility;
    }

    public String getAct() {
        return Act;
    }

    public String getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public float getAccessibility() {
        return accessibility;
    }
}
