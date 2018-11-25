package com.cs.assigntwo.question1;

public class Assignment {

    int score;

    public Assignment(){}
    public Assignment(int score){
        this.score = score;
    }

    /**
     * Override the toString method
     * @return Class representation String
     */
    @Override
    public String toString(){
        return "< Assignment >";
    }
}
