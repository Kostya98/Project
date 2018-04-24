package ru.mirea;

public class Tag {
    private String mnemo;
    private String name;

    public Tag(String mnemo, String name) {
        this.mnemo = mnemo;
        this.name = name;
    }

    public String getMnemo() {
        return mnemo;
    }

    public void setMnemo(String mnemo) {
        this.mnemo = mnemo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}