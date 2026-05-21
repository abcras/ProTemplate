package sts2silent.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import sts2silent.actions.UpdateShiv2Action;

import static sts2silent.ModFile.makeID;
public class FanOfKnivesPower extends AbstractEasyPower {
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("FanOfKnivesPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
    public FanOfKnivesPower(AbstractCreature owner) {
        super(makeID("FanOfKnivesPower"), NAME, PowerType.BUFF, false, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
