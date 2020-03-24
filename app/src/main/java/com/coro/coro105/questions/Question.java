package com.coro.coro105.questions;

/**
 * Created by JoseThomas on 3/31/2016.
 */
public class Question {
    private int id;
    private String question;
    private String optA;
    private String optB;
    private String answer;
    private int score;

    public Question(){
        id=0;
        score=0;
        question = "";
        optA = "";
        optB = "";
        answer = "";
    }

    public Question(String question, String optA, String optB, String answer,int score){
        this.question = question;
        this.optA = optA;
        this.optB = optB;
        this.answer = answer;
        this.score=score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }


    public String getOptB() {
        return optB;
    }

    public String getOptA() {
        return optA;
    }

    public String getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
