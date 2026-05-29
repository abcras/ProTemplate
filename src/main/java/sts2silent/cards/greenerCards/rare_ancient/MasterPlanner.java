package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.powers.MasterPlannerPower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class MasterPlanner extends AbstractEasyCard {
    public final static String ID = makeID("MasterPlanner");
    // intellij stuff power, self, rare, , , , , , 

    public MasterPlanner() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {


        if (!AbstractDungeon.player.hasPower(makeID("MasterPlannerPower"))) {
            applyToSelf(new MasterPlannerPower(p));
        }

    }

    public void upp() {
        upgradeBaseCost(1);
    }
}