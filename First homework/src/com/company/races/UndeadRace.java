package com.company.races;

import com.company.characters.UndeadGhoulCharacter;
import com.company.characters.UndeadMummyCharacter;
import com.company.characters.UndeadZombieCharacter;

public class UndeadRace extends AbstractRace{
    public UndeadRace(){
        name = "Нежить";
        character1 = new UndeadGhoulCharacter();
        character2 = new UndeadMummyCharacter();
        character3 = new UndeadZombieCharacter();
    }
}
