package com.company.races;

import com.company.characters.LightElementalCharacter;
import com.company.characters.LightFairyCharacter;
import com.company.characters.LightMagicianCharacter;

public class LightRace extends AbstractRace{
    public LightRace(){
        name = "Свет";
        character1 = new LightFairyCharacter();
        character2 = new LightElementalCharacter();
        character3 = new LightMagicianCharacter();
    }
}
