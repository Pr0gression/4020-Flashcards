package com.example.ultimaterecall.objects;

public class TextCard extends CardObject implements ITextCard{

    private String prompt;
    private String answer;

    public TextCard(String name,String prompt,String answer) {
        super(name);
        this.prompt = prompt;
        this.answer = answer;
    }

    public String getName() { return super.getCardName();}
    public String getPrompt() {return prompt;}
    public String getAnswer() {return answer;}
}
