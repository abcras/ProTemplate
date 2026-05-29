package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.powers.EnvenomPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Envenom extends AbstractEasyCard {
    public final static String ID = makeID("Envenom");
    // intellij stuff Envenom, self, rare, , , , , 1, 1

    public Envenom() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EnvenomPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}