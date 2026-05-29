package sts2silent.cards.greenerCards.common;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.greenerCards.Shiv2;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class BladeDance extends AbstractEasyCard {
    public final static String ID = makeID("BladeDance");
    // intellij stuff skill, self, common, , , , , 3, 1

    public BladeDance() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
        cardsToPreview = new Shiv2();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new Shiv2(), magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}