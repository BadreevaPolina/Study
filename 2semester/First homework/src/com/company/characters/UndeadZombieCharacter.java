package com.company.characters;

import com.company.skills.Skill;

public class UndeadZombieCharacter extends AbstractCharacter{

    public UndeadZombieCharacter(){
        name = "Зомби";
        firstSkill = new Skill("Рука в полете",5,5,"Кинуть руку");
        secondSkill = new Skill("Нога в полете",10,10,"Кинуть ногу");
        thirdSkill = new Skill("Зараза",15,15,"Заразить врага");
    }
}
