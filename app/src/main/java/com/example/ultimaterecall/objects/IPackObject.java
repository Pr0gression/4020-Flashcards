package com.example.ultimaterecall.objects;

public interface IPackObject {
    void randomizePack();
    int getSize();
    CardObject getCard(int i);
    CardObject[] getCards();
}
