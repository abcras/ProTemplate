package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.patches.Patches;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Abrasive extends AbstractEasyCard {
    public final static String ID = makeID("Abrasive");
    // intellij stuff power, self, rare, , , , , 4, 2

    public Abrasive() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        Patches.SlyField.sly.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, 1));
        applyToSelf(new ThornsPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}