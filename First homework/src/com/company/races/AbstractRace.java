package com.company.races;

import com.company.characters.AbstractCharacter;

public abstract class AbstractRace {//абстрактный класс для создания рас( в каждой расе по 3 персонажа)
    protected String name;
    protected int money = 45;
    protected AbstractCharacter character1;
    protected AbstractCharacter character2;
    protected AbstractCharacter character3;

    public AbstractCharacter getCharacter1() {
        return character1;
    }
    public AbstractCharacter getCharacter2() {
        return character2;
    }
    public AbstractCharacter getCharacter3() {
        return character3;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int subtractMoney(int value) {
        if (value > money) return -1;
        else
        {
            money -= value;
            return money;
        }
    }

    public void print() {
        System.out.println(name);
        System.out.println("\n");
        character1.print();
        System.out.println("\n");
        character2.print();
        System.out.println("\n");
        character3.print();
        System.out.println("\n");
    }
}
