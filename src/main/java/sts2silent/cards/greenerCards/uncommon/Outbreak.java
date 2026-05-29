package sts2silent.cards.greenerCards.uncommon;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.powers.OutbreakPower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Outbreak extends AbstractEasyCard {
    public final static String ID = makeID("Outbreak");
    // intellij stuff power, self, uncommon, , , , , 11, 4

    public Outbreak() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 11;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new OutbreakPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(4);

    }
}