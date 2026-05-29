package sts2silent.cards.greenerCards.rare_ancient;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.powers.ShadowmeldPower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Shadowmeld extends AbstractEasyCard {
    public final static String ID = makeID("Shadowmeld");
    // intellij stuff skill, self, rare, , , , , , 

    public Shadowmeld() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ShadowmeldPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}