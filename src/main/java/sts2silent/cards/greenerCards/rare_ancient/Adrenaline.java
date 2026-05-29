package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Adrenaline extends AbstractEasyCard {
    public final static String ID = makeID("Adrenaline");
    // intellij stuff skill, self, rare, , , , , 1, 2

    public Adrenaline() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        draw(2);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}