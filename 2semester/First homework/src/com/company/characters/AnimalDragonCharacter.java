package com.company.characters;

import com.company.skills.Skill;

public class AnimalDragonCharacter extends AbstractCharacter{

    public AnimalDragonCharacter(){
        name = "Дракон";
        firstSkill = new Skill("Крылья",5,5,"Налететь на врага");
        secondSkill = new Skill("Тяжесть",10,10,"Сесть на врага");
        thirdSkill = new Skill("Огонь",15,15,"Метнуть огонь");
    }
}
