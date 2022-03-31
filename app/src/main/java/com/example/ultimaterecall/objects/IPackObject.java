package com.example.ultimaterecall.objects;

import java.util.ArrayList;

public interface IPackObject {
    void randomizePack();
    int getSize();
    CardObject getCard(int i);
    ArrayList<CardObject> getCards();
}
