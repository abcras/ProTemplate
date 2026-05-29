package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.actions.unique.ApplyBulletTimeAction;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class BulletTime extends AbstractEasyCard {
    public final static String ID = makeID("BulletTime");
    // intellij stuff skill, self, rare, , , , , , 

    public BulletTime() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NoDrawPower(p));
        this.addToBot(new ApplyBulletTimeAction());
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}