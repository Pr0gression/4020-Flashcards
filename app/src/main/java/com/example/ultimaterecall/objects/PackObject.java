package com.example.ultimaterecall.objects;

public class PackObject implements IPackObject {

    private String packName;
    private int packSize;
    private String packDesc;
    private CardObject cards[];

    public PackObject(String packName, int packSize, String packDesc, CardObject pack[]) {
        this.packName = packName;
        this.packSize = packSize;
        this.packDesc = packDesc;
        this.cards = pack;

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

    @Override
    public void createPack() {
        //Create new pack
    }

    @Override
    public void randomizePack() {

    }
}
