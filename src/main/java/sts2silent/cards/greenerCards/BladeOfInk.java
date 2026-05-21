package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class BladeOfInk extends AbstractEasyCard {
    public final static String ID = makeID("BladeOfInk");
    // intellij stuff skill, self, rare, , , , , 2, 1

    public BladeOfInk() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new Shiv2(true), magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}