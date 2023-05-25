package com.company.characters;

import com.company.skills.Skill;

public class LightMagicianCharacter extends AbstractCharacter{

    public LightMagicianCharacter(){
        name = "Маг";
        firstSkill = new Skill("Удар", 5, 5, "Ударить волшебной палочкой по голове");
        secondSkill = new Skill("Заклинание",10,10,"Использовать заклинание");
        thirdSkill = new Skill("Чары", 15, 15, "Наложить сильные чары");
    }
}
