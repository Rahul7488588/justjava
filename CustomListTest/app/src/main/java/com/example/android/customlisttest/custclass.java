package com.example.android.customlisttest;

public class custclass {
    private String names;
    private String address;
    private long classes;
    private String urls;
    public custclass(String names, String address, long classes, String urls ) {
        this.names = names;
        this.address = address;
        this.classes = classes;
        this.urls=urls;
    }

    public String getNames() {
        return names;
    }

    public String getAddress() {
        return address;
    }

    public long getClasses() {
        return classes;
    }

    public String getUrls() {
        return urls;
    }
}
