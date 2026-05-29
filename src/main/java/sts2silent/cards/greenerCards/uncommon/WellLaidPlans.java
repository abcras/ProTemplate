package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.powers.RetainCardPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class WellLaidPlans extends AbstractEasyCard {
    public final static String ID = makeID("WellLaidPlans");
    // intellij stuff power, self, uncommon, , , , , 1, 1

    public WellLaidPlans() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RetainCardPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}