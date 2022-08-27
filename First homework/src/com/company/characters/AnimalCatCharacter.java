package com.company.characters;

import com.company.skills.Skill;

public class AnimalCatCharacter extends AbstractCharacter{

    public AnimalCatCharacter(){
        name = "Говорящий кот";
        firstSkill = new Skill("Угрозы",5,5,"Угрожать врагу");
        secondSkill = new Skill("Цапнуть",10,10,"Цапнуть за руку");
        thirdSkill = new Skill("Расцарапать",15,15,"Расцарапать лицо");
    }
}
