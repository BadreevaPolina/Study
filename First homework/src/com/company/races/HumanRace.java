package com.company.races;

import com.company.characters.HumanArcherCharacter;
import com.company.characters.HumanDefenderCharacter;
import com.company.characters.HumanWarriorCharacter;

public class HumanRace extends AbstractRace{
    public HumanRace() {
        name = "Человеки";
        character1 = new HumanWarriorCharacter();
        character2 = new HumanArcherCharacter();
        character3 = new HumanDefenderCharacter();
    }
}
