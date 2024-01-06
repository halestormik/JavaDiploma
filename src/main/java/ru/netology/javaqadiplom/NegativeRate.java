package ru.netology.javaqadiplom;

public class NegativeRate extends RuntimeException{
    public NegativeRate(String rate){
        super(rate);
    }
}
