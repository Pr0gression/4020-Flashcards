package com.example.ultimaterecall.objects;

public abstract class CardObject implements ICardObject {
    private String cardName;

    public CardObject(String name) {
        this.cardName = name;

    }
}
