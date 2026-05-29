package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Expertise extends AbstractEasyCard {
    public final static String ID = makeID("Expertise");
    // intellij stuff skill, self, uncmmon, , , , , 6, 7

    public Expertise() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ExpertiseAction(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(7);

    }
}