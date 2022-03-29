package com.example.ultimaterecall.objects;

public class MultipleChoiceCard extends CardObject implements IMultipleChoiceCard {

    private String prompt;
    private String answers[] = new String[4];
    private int index;

    public MultipleChoiceCard(String name, String[] answers, int correctAnswer) {
        super(name);
        this.answers = answers;
        this.index = correctAnswer;
    }

    public String getPrompt() { return prompt; }
    public String[] getAnswers() { return answers; }
    public int getAnswerIndex() { return index; }
}
