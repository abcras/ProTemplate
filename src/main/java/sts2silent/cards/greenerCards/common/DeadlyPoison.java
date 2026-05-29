package sts2silent.cards.greenerCards.common;

import com.megacrit.cardcrawl.powers.PoisonPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class DeadlyPoison extends AbstractEasyCard {
    public final static String ID = makeID("DeadlyPoison");
    // intellij stuff skill, enemy, common, , , , , 5, 2

    public DeadlyPoison() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}