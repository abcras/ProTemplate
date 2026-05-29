package sts2silent.cards.greenerCards.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class Backflip extends AbstractEasyCard {
    public final static String ID = makeID("Backflip");
    // intellij stuff skill, self, common, , , 5, 3, 2, 

    public Backflip() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
        baseMagicNumber = magicNumber = 2;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DrawCardAction(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(3);

    }
}