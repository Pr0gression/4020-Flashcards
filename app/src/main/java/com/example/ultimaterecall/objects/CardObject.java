package com.example.ultimaterecall.objects;

public class CardObject implements ICardObject {
    private String cardName;
    private boolean isEnabled;

    public CardObject(String name) {
        this.cardName = name;
        this.isEnabled = false;
    }

    public String getCardName() {return cardName;}
    public boolean isEnabled() { return isEnabled;}
    public void toggleEnabled() {
        if (isEnabled) {
            isEnabled = false;
        } else {
            isEnabled = true;
        }
    }
}
