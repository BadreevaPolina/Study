package com.company.characters;

import com.company.skills.Skill;

public class HumanDefenderCharacter extends AbstractCharacter{

    public HumanDefenderCharacter(){
        name = "Защитник";
        firstSkill = new Skill("Слабый щит",5,5,"Щит на 1 человека");
        secondSkill = new Skill("Среднмй щит",10,10,"Щит на 2 человек");
        thirdSkill = new Skill("Большой щит",15,15,"Щит на 3 человек");
    }
}
