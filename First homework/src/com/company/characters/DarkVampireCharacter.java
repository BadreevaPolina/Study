package com.company.characters;

import com.company.skills.Skill;

public class DarkVampireCharacter extends AbstractCharacter{

    public DarkVampireCharacter(){
        name = "Вампир";
        firstSkill = new Skill("Рука",5,5,"Укусить руку");
        secondSkill = new Skill("Нога",10,10,"Укусить ногу");
        thirdSkill = new Skill("Шея",15,15,"Укусить шею");

    }
}
