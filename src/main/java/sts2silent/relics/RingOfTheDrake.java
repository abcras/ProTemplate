package sts2silent.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sts2silent.CharacterFile;

import static sts2silent.ModFile.makeID;

public class RingOfTheDrake extends AbstractEasyRelic {
    public static final String ID = makeID("RingOfTheDrake");
    int turnCount = 0;

    public RingOfTheDrake() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, CharacterFile.Enums.Greener);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 2 + this.DESCRIPTIONS[1];
    }


    @Override
    public void atTurnStart() {
        if (turnCount < 3) {

            turnCount++;
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));
        } else {
            this.grayscale = true;
        }
    }

    @Override
    public void onVictory() {
        turnCount = 0;
        this.grayscale = false;
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(RingOfTheSnake2.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
                if (((AbstractRelic)AbstractDungeon.player.relics.get(i)).relicId.equals(RingOfTheSnake2.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(RingOfTheSnake2.ID);
    }

    /*@Override
    public void atBattleStart() {
        this.flash();
        //this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new DrawCardAction(AbstractDungeon.player, 2));
    }*/
}
