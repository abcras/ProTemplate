package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.actions.unique.NightmareAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Nightmare extends AbstractEasyCard {
    public final static String ID = makeID("Nightmare");
    // intellij stuff skill, self, rare, , , , , , 

    public Nightmare() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new NightmareAction(p, p, 3));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}