package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.cards.DamageInfo;
import sts2silent.actions.TheHuntAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class TheHunt extends AbstractEasyCard {
    public final static String ID = makeID("TheHunt");
    // intellij stuff attack, enemy, rare, 10, 3, , , , 

    public TheHunt() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TheHuntAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }

    public void upp() {
        upgradeDamage(3);

    }
}