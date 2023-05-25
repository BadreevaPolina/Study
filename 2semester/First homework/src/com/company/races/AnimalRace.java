package com.company.races;

import com.company.characters.AnimalCatCharacter;
import com.company.characters.AnimalDragonCharacter;
import com.company.characters.AnimalUnicornCharacter;

public class AnimalRace extends AbstractRace{
    public AnimalRace(){
        name = "Животные";
        character1 = new AnimalCatCharacter();
        character2 = new AnimalDragonCharacter();
        character3 = new AnimalUnicornCharacter();
    }
}
