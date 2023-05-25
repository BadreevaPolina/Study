package com.company.characters;

import com.company.skills.Skill;

public class LightElementalCharacter extends AbstractCharacter{

    public LightElementalCharacter(){
        name = "Стихийник";
        firstSkill = new Skill("Воздух", 5, 5, "Воздушная сфера");
        secondSkill = new Skill("Огонь + Вода",10,10,"Огненная вода");
        thirdSkill = new Skill("Комбо", 15, 15, "4 стихии");
    }
}
