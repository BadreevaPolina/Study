package com.company.characters;

import com.company.skills.Skill;

public class AnimalUnicornCharacter extends AbstractCharacter{

    public AnimalUnicornCharacter(){
        name = "Единорог";
        firstSkill = new Skill("Ржание",5,5,"Громко заржать");
        secondSkill = new Skill("Рог",10,10,"Бежать на противника");
        thirdSkill = new Skill("Гипноз",15,15,"Загипнотизировать врага");
    }
}
