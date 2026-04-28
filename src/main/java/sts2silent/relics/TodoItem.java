package sts2silent.relics;

import sts2silent.CharacterFile;

import static sts2silent.ModFile.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.Greener);
    }
}
