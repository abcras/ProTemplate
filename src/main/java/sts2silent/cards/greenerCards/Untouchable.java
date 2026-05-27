package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.patches.SlyPatch;

import static sts2silent.ModFile.makeID;

public class Untouchable extends AbstractEasyCard {
    public final static String ID = makeID("Untouchable");
    // intellij stuff skill, self, common, , , 6, 2, , 

    public Untouchable() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        SlyPatch.SlyField.sly.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        DoThing();
    }

    public void DoThing(){
        blck();
    }

    /*@Override
    public void triggerOnManualDiscard() {
        DoThing();
    }*/

    public void upp() {
        upgradeBlock(2);
    }
}