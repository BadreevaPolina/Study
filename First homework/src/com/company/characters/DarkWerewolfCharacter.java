package com.company.characters;

import com.company.skills.Skill;

public class DarkWerewolfCharacter extends AbstractCharacter {

    public DarkWerewolfCharacter(){
        name = "Оборотень";
        firstSkill = new Skill("Подзатыльник",5,5,"Дать подзатыльник");
        secondSkill = new Skill("Пнуть",10,10,"Пнуть врага");
        thirdSkill = new Skill("Прыгнуть",15,15,"Прыгнуть на врага");
    }
}
