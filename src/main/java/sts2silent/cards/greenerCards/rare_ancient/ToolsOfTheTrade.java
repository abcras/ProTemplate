package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.powers.ToolsOfTheTradePower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class ToolsOfTheTrade extends AbstractEasyCard {
    public final static String ID = makeID("ToolsOfTheTrade");
    // intellij stuff power, self, rare, , , , , , 

    public ToolsOfTheTrade() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ToolsOfTheTradePower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}