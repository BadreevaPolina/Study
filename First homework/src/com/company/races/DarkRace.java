package com.company.races;

import com.company.characters.DarkVampireCharacter;
import com.company.characters.DarkWerewolfCharacter;
import com.company.characters.DarkWitchCharacter;

public class DarkRace extends AbstractRace{
    public DarkRace(){
        name = "Тьма";
        character1 = new DarkWerewolfCharacter();
        character2 = new DarkWitchCharacter();
        character3 = new DarkVampireCharacter();
    }
}
