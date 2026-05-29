package sts2silent.cards.greenerCards.common;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.greenerCards.Shiv2;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class CloakAndDagger extends AbstractEasyCard {
    public final static String ID = makeID("CloakAndDagger");
    // intellij stuff skill, self, common, , , 6, , 1, 1

    public CloakAndDagger() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Shiv2();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        makeInHand(new Shiv2(), magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}