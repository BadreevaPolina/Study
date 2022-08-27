package com.company.characters;

import com.company.skills.Skill;

public class UndeadMummyCharacter extends AbstractCharacter{

    public UndeadMummyCharacter(){
        name = "Мумия";
        firstSkill = new Skill("Гроб",5,5,"Кинуть гроб");
        secondSkill = new Skill("Зараза",10,10,"Заразить врага");
        thirdSkill = new Skill("Песок",15,15,"Рассыпаться песком");
    }
}
