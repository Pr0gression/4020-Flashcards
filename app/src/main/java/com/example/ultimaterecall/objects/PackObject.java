package com.example.ultimaterecall.objects;

public class PackObject {

    private String packName;
    private int packSize;


    public PackObject(String packName, int packSize) {
        this.packName = packName;
        this.packSize = packSize;
    }

    public String getName() { return this.packName;}
    public int getSize() { return this.packSize;}

    public void setName(String newName) {
        this.packName = newName;
    }
    public void setSize(int newSize) {
        this.packSize = newSize;
    }
}
