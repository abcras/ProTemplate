package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sts2silent.actions.EasyXCostAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Malaise extends AbstractEasyCard {
    public final static String ID = makeID("Malaise");
    // intellij stuff skill, enemy, rare, , , , , ,

    public Malaise() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 0;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {

            applyToEnemy(m, new StrengthPower(m, -(effect + params[0])));

            applyToEnemy(m, new WeakPower(m, effect + params[0], false));
            return true;
        }, magicNumber));
    }
    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }
}