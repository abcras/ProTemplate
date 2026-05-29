package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Acrobatics extends AbstractEasyCard {
    public final static String ID = makeID("Acrobatics");
    // intellij stuff SKILL, SELF, UNCOMMON, , , , , 3, 1

    public Acrobatics() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        discard(1);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}