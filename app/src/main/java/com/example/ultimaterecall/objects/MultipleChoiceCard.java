package com.example.ultimaterecall.objects;

public class MultipleChoiceCard extends CardObject implements IMultipleChoiceCard {

    private String prompt;
    private String[] answers;
    private int index;

    public MultipleChoiceCard(String name,String prompt, String[] answers, int correctAnswer) {
        super(name);
        this.prompt = prompt;
        this.answers = answers;
        if (correctAnswer < 3 && correctAnswer > 0) {
            this.index = correctAnswer;
        } else {
            throw new IllegalArgumentException("Error: correct answer must be valid");
        }
    }

    public String getPrompt() { return prompt; }
    public String[] getAnswers() { return answers; }
    public int getAnswerIndex() { return index; }
}
