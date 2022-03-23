package com.example.ultimaterecall.objects;

public class PackObject {

    private String packName;
    private int packSize;
    private String packDesc;


    public PackObject(String packName, int packSize, String packDesc) {
        this.packName = packName;
        this.packSize = packSize;
        this.packDesc = packDesc;
    }

    public String getName() { return this.packName;}
    public int getSize() { return this.packSize;}
    public String getDesc() { return this.packDesc;}

    public void setName(String newName) {
        this.packName = newName;
    }
    public void setSize(int newSize) {
        this.packSize = newSize;
    }
    public void setDesc(String newDesc) {
        this.packDesc = newDesc;
    }


}
