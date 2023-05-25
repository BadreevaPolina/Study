package com.company.characters;

import com.company.skills.Skill;

public abstract class AbstractCharacter {// абсрактный класс, который помогает расписать способности персонажей

    protected String name;
    protected Skill firstSkill;
    protected Skill secondSkill;
    protected Skill thirdSkill;

    public String getName() {
        return name;
    }
    public Skill getFirstSkill() {
        return firstSkill;
    }
    public Skill getSecondSkill() {
        return secondSkill;
    }
    public Skill getThirdSkill() {
        return thirdSkill;
    }

    public void print() {
        System.out.println(name);
        firstSkill.print();
        secondSkill.print();
        thirdSkill.print();
    }
}
