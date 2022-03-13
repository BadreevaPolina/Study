package com.company.characters;

import com.company.skills.Skill;

public class LightFairyCharacter extends AbstractCharacter{

    public LightFairyCharacter(){
        name = "Фея";
        firstSkill = new Skill("Крылья", 5, 5, "Ударить крыльями");
        secondSkill = new Skill("Свет",10,10,"Ослепить врага");
        thirdSkill = new Skill("Пыльца", 15, 15, "Распылить пыльцу фей");
    }
}
