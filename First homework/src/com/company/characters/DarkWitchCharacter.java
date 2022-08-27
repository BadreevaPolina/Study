package com.company.characters;

import com.company.skills.Skill;

public class DarkWitchCharacter extends AbstractCharacter{

    public DarkWitchCharacter(){
        name = "Ведьма";
        firstSkill = new Skill("Зелье",5,5,"Кинуть зелье");
        secondSkill = new Skill("Порча",10,10,"Наложить порчу");
        thirdSkill = new Skill("Проклятье",15,15,"Проклянуть");

    }
}
