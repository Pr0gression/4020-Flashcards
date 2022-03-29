package com.example.ultimaterecall.objects;

public class PackObject implements IPackObject {

    private String packName;
    private int packSize;
    private String packDesc;
    private CardObject[] cards;

    public PackObject(String packName, int packSize, String packDesc, CardObject[] pack) {
        this.packName = packName;
        this.packSize = packSize;
        this.packDesc = packDesc;
        this.cards = pack;
    }

    public int getSize() { return this.packSize;}
    public String getName() { return this.packName;}
    public String getDesc() { return this.packDesc;}
    public void setDesc(String newDesc) {
        this.packDesc = newDesc;
    }
    public void setName(String newName) { this.packName = newName; }

    @Override
    public CardObject getCard(int i) {
        return cards[i];
    }

    @Override
    public CardObject[] getCards() {
        return cards;
    }

    @Override
    public void randomizePack() {
        //Might be implemented later
    }
}
