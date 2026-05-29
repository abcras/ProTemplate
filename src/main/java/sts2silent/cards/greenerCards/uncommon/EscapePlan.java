package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class EscapePlan extends AbstractEasyCard {
    public final static String ID = makeID("EscapePlan");
    // intellij stuff skill, self, uncmmon, , , 3, 2, 1, 

    public EscapePlan() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 3;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(magicNumber, new EscapePlanAction(this.block)));
    }

    public void upp() {
        upgradeBlock(2);

    }
}