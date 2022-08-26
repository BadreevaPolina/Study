package com.company.skills;

public class Skill { // данные о способностях
    protected String name;
    protected String description;
    protected int damage;
    protected int cost;

    public Skill(String name, int cost, int damage, String description) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public void print() {
        System.out.printf("%s цена: %d\n", name, cost);
        System.out.println(description);
    }
}
