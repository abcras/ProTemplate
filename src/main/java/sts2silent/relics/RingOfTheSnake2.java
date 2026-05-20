package sts2silent.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.CharacterFile;

import static sts2silent.ModFile.makeID;

public class RingOfTheSnake2 extends AbstractEasyRelic {
    public static final String ID = makeID("RingOfTheSnake2");

    public RingOfTheSnake2() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.Greener);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 2 + this.DESCRIPTIONS[1];
    }

    @Override
    public void atBattleStart() {
        this.flash();
        //this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));
    }
}
