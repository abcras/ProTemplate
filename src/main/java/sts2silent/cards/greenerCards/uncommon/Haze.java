package sts2silent.cards.greenerCards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.common.AllEnemyApplyPowerAction;
import com.megacrit.cardcrawl.powers.PoisonPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.patches.Patches;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Haze extends AbstractEasyCard {
    public final static String ID = makeID("Haze");
    // intellij stuff skill, all_enemy, uncmmon, , , , , 4, 2

    public Haze() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 4;
        Patches.SlyField.sly.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AllEnemyApplyPowerAction(p, magicNumber,  (a) -> new PoisonPower(p,a, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}