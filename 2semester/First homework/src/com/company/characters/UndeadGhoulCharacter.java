package com.company.characters;

import com.company.skills.Skill;

public class UndeadGhoulCharacter extends AbstractCharacter{

    public UndeadGhoulCharacter(){
        name = "Упырь";
        firstSkill = new Skill("Прикоснуться",5,5,"Прикоснуться к врагу");
        secondSkill = new Skill("Поцарапать",10,10,"Поцарапать врага");
        thirdSkill = new Skill("Укусить",15,15,"Укусить врага");
    }
}
