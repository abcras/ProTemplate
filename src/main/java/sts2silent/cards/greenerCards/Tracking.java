package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import sts2silent.powers.TrackingEnemyPower;
import sts2silent.powers.TrackingPower;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Tracking extends AbstractEasyCard {
    public final static String ID = makeID("Tracking");
    // intellij stuff power, self, rare, , , , , 2, 

    public Tracking() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TrackingPower(p, magicNumber));
        /*for(AbstractMonster mon : AbstractDungeon.getMonsters().monsters)
        {
            applyToEnemy(mon, new TrackingEnemyPower(mon));
        }*/
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}