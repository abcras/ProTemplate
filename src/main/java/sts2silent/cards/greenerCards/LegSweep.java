package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.powers.WeakPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class LegSweep extends AbstractEasyCard {
    public final static String ID = makeID("LegSweep");
    // intellij stuff skill, enemy, uncmmon, , , 11, 3, 2, 1

    public LegSweep() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseBlock = 11;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);

    }
}