package com.example.ultimaterecall.objects;

public class MultipleChoiceCard extends CardObject implements IMultipleChoiceCard {
    private static final int MAX_ANSWERS = 3;

    private String prompt;
    private String[] answers;
    private int index;

    public MultipleChoiceCard(String name,String prompt, String[] answers, int correctAnswer) {
        super(name);
        this.prompt = prompt;
        this.answers = answers;
        if(answers.length > MAX_ANSWERS)
            throw new IllegalArgumentException("Error: Multiple choice card can have at most " + MAX_ANSWERS + "answers");
        if (correctAnswer < answers.length && correctAnswer >= 0) {
            this.index = correctAnswer;
        } else {
            throw new IllegalArgumentException("Error: correct answer must be valid");
        }
    }

    public String getPrompt() { return prompt; }
    public String[] getAnswers() { return answers; }
    public int getAnswerIndex() { return index; }
}
