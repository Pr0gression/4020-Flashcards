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
        TextCard pack1card1 = new TextCard("Card1","Konnichiwa","Hello");
        TextCard pack1card2 = new TextCard("Card2","Sayonara","Goodbye");
        TextCard pack1card3 = new TextCard("Card3","Aruku","To Walk");
        TextCard pack1card4 = new TextCard("Card4","Hashiru","To Run");
        TextCard pack1card5 = new TextCard("Card5","Tobu","To Fly");
        TextCard pack1card6 = new TextCard("Card6","Taberu","To Eat");
        TextCard pack1card7 = new TextCard("Card7","Nomu","To Drink");
        ArrayList<CardObject> pack1 = new ArrayList<>();
        Collections.addAll(pack1, pack1card1,pack1card2,pack1card3,pack1card4,pack1card5,pack1card6,pack1card7);

        pack1card1.toggleEnabled();

        TextCard pack2card1 = new TextCard("Card1","Q1","A1");
        TextCard pack2card2 = new TextCard("Card2","Q2","A2");
        TextCard pack2card3 = new TextCard("Card3","Q3","A3");
        TextCard pack2card4 = new TextCard("Card4","Q4","A4");
        TextCard pack2card5 = new TextCard("Card5","Q5","A5");
        TextCard pack2card6 = new TextCard("Card6","Q6","A6");
        TextCard pack2card7 = new TextCard("Card7","Q7","A7");
        MultipleChoiceCard pack2card8 = new MultipleChoiceCard("Card 8","Q8", new String[]{"A1", "A2", "A3"},1);

        ArrayList<CardObject> pack2 = new ArrayList<>();
        Collections.addAll(pack2, pack2card1,pack2card2,pack2card3,pack2card4,pack2card5,pack2card6,pack2card7,pack2card8);

        PackObject p1 = new PackObject("Japanese",7,"Learn Japanese now!",pack1);
        PackObject p2 = new PackObject("Other",8,"Learn Other now!",pack2);

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
