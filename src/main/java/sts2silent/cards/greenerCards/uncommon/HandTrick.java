package sts2silent.cards.greenerCards.uncommon;

import sts2silent.actions.AddSlyAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class HandTrick extends AbstractEasyCard {
    public final static String ID = makeID("HandTrick");
    // intellij stuff skill, self, uncommon, , , 7, 3, , 

    public HandTrick() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new AddSlyAction(1, true));
    }

    public void upp() {
        upgradeBlock(3);

    }
}