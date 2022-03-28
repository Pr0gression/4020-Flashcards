package com.example.ultimaterecall.objects;

public class MultipleChoiceCard extends CardObject {

    private String prompt;

    public MultipleChoiceCard(String name) {
        super(name);
    }

    @Override
    public String getPrompt() { return prompt; }
}
