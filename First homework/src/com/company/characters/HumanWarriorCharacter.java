package com.company.characters;

import com.company.skills.Skill;

public class HumanWarriorCharacter extends AbstractCharacter{

    public HumanWarriorCharacter() {
        name = "Воин";
        firstSkill = new Skill("Пинок", 5, 5, "Пинает врага ногой");
        secondSkill = new Skill("Оглушение",10,10,"Оглушает врага щитом");
        thirdSkill = new Skill("Удар мечом", 15, 15, "Скучный удар мечом");
    }
}
