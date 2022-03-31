package com.example.ultimaterecall.data;

import com.example.ultimaterecall.objects.CardObject;
import com.example.ultimaterecall.objects.MultipleChoiceCard;
import com.example.ultimaterecall.objects.PackObject;
import com.example.ultimaterecall.objects.TextCard;

import java.util.ArrayList;
import java.util.Collections;

public class FakeDatabase {

    ArrayList<PackObject> packs;
    private int numPacks;

    public FakeDatabase() {
        packs = new ArrayList<>();
        numPacks = 2;
        makeData();
    }

    private void makeData() {
        ArrayList<CardObject> pack1 = new ArrayList<>();
        pack1.add(new TextCard("Card 1a", "omena", "apple"));
        pack1.add(new TextCard("Card 2a", "nainen", "woman"));
        pack1.add(new TextCard("Card 3a", "koira", "dog"));
        pack1.add(new TextCard("Card 4a", "hyvastit", "goodbye"));
        pack1.add(new TextCard("Card 5a", "syoda", "to eat"));
        pack1.add(new TextCard("Card 6a", "suorittaa", "to run"));
        pack1.add(new TextCard("Card 7a", "lapsi", "child"));

        pack1.add(new MultipleChoiceCard("Card 5b", "syoda", new String[]{"child", "to eat", "apple"}, 1));
        pack1.add(new MultipleChoiceCard("Card 1b", "omena", new String[]{"apple", "banana", "to run"}, 0));
        pack1.add(new MultipleChoiceCard("Card 6b", "suorittaa", new String[]{"to fly", "man", "to run"}, 2));
        pack1.add(new MultipleChoiceCard("Card 4b", "hyvastit", new String[]{"goodbye", "to sit", "woman"}, 0));
        pack1.add(new MultipleChoiceCard("Card 2b", "nainen", new String[]{"man", "woman", "apple"}, 1));
        pack1.add(new MultipleChoiceCard("Card 3b", "koira", new String[]{"cat", "goodbye", "dog"}, 2));
        pack1.add(new MultipleChoiceCard("Card 7b", "lapsi", new String[]{"child", "sleep", "dog"}, 0));

        pack1.add(new TextCard("Card 4c", "hyvastit", "goodbye"));
        pack1.add(new TextCard("Card 5c", "syoda", "to eat"));
        pack1.add(new TextCard("Card 1c", "omena", "apple"));
        pack1.add(new TextCard("Card 3c", "koira", "dog"));
        pack1.add(new TextCard("Card 7c", "lapsi", "child"));
        pack1.add(new TextCard("Card 2c", "nainen", "woman"));
        pack1.add(new TextCard("Card 6c", "suorittaa", "to run"));

        pack1.get(0).toggleEnabled();

        ArrayList<CardObject> pack2 = new ArrayList<>();
        pack2.add(new TextCard("Card 1a", "lo", "sleep"));
        pack2.add(new TextCard("Card 2a", "ibili", "to walk"));
        pack2.add(new TextCard("Card 3a", "platano", "banana"));
        pack2.add(new TextCard("Card 4a", "nagusi", "adult"));
        pack2.add(new TextCard("Card 5a", "joko", "game"));
        pack2.add(new TextCard("Card 6a", "gizon", "man"));
        pack2.add(new TextCard("Card 7a", "edan", "to drink"));

        pack2.add(new MultipleChoiceCard("Card 2b", "ibili", new String[]{"woman", "to walk", "dog"}, 1));
        pack2.add(new MultipleChoiceCard("Card 1b", "lo", new String[]{"sleep", "apple", "to run"}, 0));
        pack2.add(new MultipleChoiceCard("Card 5b", "joko", new String[]{"child", "game", "apple"}, 1));
        pack2.add(new MultipleChoiceCard("Card 7b", "edan", new String[]{"to drink", "to run", "dog"}, 0));
        pack2.add(new MultipleChoiceCard("Card 3b", "platano", new String[]{"cat", "sleep", "banana"}, 2));
        pack2.add(new MultipleChoiceCard("Card 4b", "nagusi", new String[]{"adult", "child", "man"}, 0));
        pack2.add(new MultipleChoiceCard("Card 6b", "gizon", new String[]{"to fly", "game", "man"}, 2));

        pack2.add(new TextCard("Card 1c", "lo", "sleep"));
        pack2.add(new TextCard("Card 4c", "nagusi", "adult"));
        pack2.add(new TextCard("Card 6c", "gizon", "man"));
        pack2.add(new TextCard("Card 5c", "joko", "game"));
        pack2.add(new TextCard("Card 3c", "platano", "banana"));
        pack2.add(new TextCard("Card 2c", "ibili", "to walk"));
        pack2.add(new TextCard("Card 7c", "edan", "to drink"));

        PackObject p1 = new PackObject("Finnish",21,"Learn Finnish now!",pack1);
        PackObject p2 = new PackObject("Basque",21,"Learn Basque now!",pack2);

        packs.add(p1);
        packs.add(p2);
    }

    public ArrayList<PackObject> getPacks() {
        return packs;
    }
    public PackObject getPack(int i) { return packs.get(i);}

    public int getNumPacks() {
        return numPacks;
    }
}
