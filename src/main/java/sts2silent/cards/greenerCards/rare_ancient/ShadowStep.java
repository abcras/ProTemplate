package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.powers.PhantasmalPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class ShadowStep extends AbstractEasyCard {
    public final static String ID = makeID("ShadowStep");
    // intellij stuff skill, self, rare, , , , , , 

    public ShadowStep() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        discard(p.hand.size());
        applyToSelf(new PhantasmalPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}