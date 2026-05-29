package sts2silent.cards.greenerCards.rare_ancient;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.powers.AccelerantPower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Accelerant extends AbstractEasyCard {
    public final static String ID = makeID("Accelerant");
    // intellij stuff power, self, rare, , , , , 1, 1

    public Accelerant() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
            applyToSelf(new AccelerantPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}