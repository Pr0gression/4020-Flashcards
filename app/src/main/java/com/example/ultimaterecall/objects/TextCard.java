package com.example.ultimaterecall.objects;

public class TextCard extends CardObject {

    private String prompt;
    private String answer;

    public TextCard(String name,String prompt,String answer) {
        super(name);
        this.prompt = prompt;
        this.answer = answer;
    }
}
