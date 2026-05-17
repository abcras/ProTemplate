package sts2silent.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.discard;

public class Survivor extends AbstractEasyCard {
    public final static String ID = makeID("Survivor");
    // intellij stuff ATTACK, ENEMY, Common, 3, 1, , , ,

    public Survivor() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 8;
        //magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        discard(1);
        //dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        //applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
    }

    public void upp() {
        upgradeBlock(3);
    }
}