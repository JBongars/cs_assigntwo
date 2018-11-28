package com.cs.assigntwo.question1;

public class Assignment {

    int score;

    public Assignment(){}
    public Assignment(int score){
        this.score = score;
    }

    /**
     * Getter method for score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter method for score
     * @param score the current score
     */
    public void setScore(int score) {
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
