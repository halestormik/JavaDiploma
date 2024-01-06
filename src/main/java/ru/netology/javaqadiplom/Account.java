package ru.netology.javaqadiplom;

public class Account {
    protected int balance;
    protected int rate;

    public boolean pay(int amount) { // покупка
        return false;
    }

    public boolean add(int amount) { // пополнение
        return false;
    }

    public int yearChange() { // прогноз процентов за год
        return 0;
    }

    public int getBalance() {
        return balance;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
