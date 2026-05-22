/*
package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import sts2silent.ModFile;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class Predator_V01050 extends AbstractEasyCard {
    public final static String ID = makeID("Predator");
    // intellij stuff attack, monster, uncommon, 15, 5, , , ,


    public Predator_V01050() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 2), 2));
    }

    public void upp() {
        upgradeDamage(5);

    }
}*/
