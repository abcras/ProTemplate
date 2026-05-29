package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.powers.DexterityPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Footwork extends AbstractEasyCard {
    public final static String ID = makeID("Footwork");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public Footwork() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}