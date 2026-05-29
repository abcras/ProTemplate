package sts2silent.cards.greenerCards.common;

import com.megacrit.cardcrawl.powers.PoisonPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Snakebite extends AbstractEasyCard {
    public final static String ID = makeID("Snakebite");
    // intellij stuff skill, enemy, common, , , , , 7, 3

    public Snakebite() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 7;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);

    }
}