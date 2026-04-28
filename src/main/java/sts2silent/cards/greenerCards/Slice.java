package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import static sts2silent.CharacterFile.Enums.Greener;

import static sts2silent.ModFile.makeID;

public class Slice extends AbstractEasyCard {
    public final static String ID = makeID("Slice");
    // intellij stuff ATTACK, ENEMY, COMMON, 6, 3, 0, 0, 0, 0

    public Slice() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        //Unnecessary color declaration
        color = Greener;
        baseDamage = 6;
        baseBlock = 0;
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(0);
        upgradeMagicNumber(0);
        upgradeBaseCost(0);
    }
}