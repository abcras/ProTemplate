package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class CalculatedGamble extends AbstractEasyCard {
    public final static String ID = makeID("CalculatedGamble");
    // intellij stuff skill, self, uncommon, , , , , , 

    public CalculatedGamble() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = p.hand.size();
        atb(new DiscardAction(p, p, count, true));
        atb(new DrawCardAction(count));

    }

    public void upp() {
        selfRetain = true;
    }
}