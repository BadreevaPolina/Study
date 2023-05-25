package com.company.characters;

import com.company.skills.Skill;

public class HumanArcherCharacter extends AbstractCharacter {

    public HumanArcherCharacter(){
        name = "Лучник";
        firstSkill = new Skill("Задеть",5,5,"Слегка задеть плечо");
        secondSkill = new Skill("Ранить",10,10,"Попасть в плечо");
        thirdSkill = new Skill("Сильно ранить",15,15,"Попасть в грудь");
    }
}
