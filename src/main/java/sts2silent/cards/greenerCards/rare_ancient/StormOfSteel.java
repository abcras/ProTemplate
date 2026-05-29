package sts2silent.cards.greenerCards.rare_ancient;

import sts2silent.actions.StormOfSteelAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class StormOfSteel extends AbstractEasyCard {
    public final static String ID = makeID("StormOfSteel");
    // intellij stuff skill, self, rare, , , , , , 

    public StormOfSteel() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new StormOfSteelAction(this.upgraded));
    }

    public void upp() {

    }
}