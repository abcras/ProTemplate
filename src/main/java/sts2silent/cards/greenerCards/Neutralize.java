package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.powers.WeakPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Neutralize extends AbstractEasyCard {
    public final static String ID = makeID("Neutralize");
    // intellij stuff ATTACK, ENEMY, Common, 3, 1, , , , 

    public Neutralize() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 3;
        magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}