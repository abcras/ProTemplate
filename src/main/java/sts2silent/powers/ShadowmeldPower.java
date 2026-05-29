package sts2silent.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sts2silent.ModFile.makeID;

public class ShadowmeldPower extends AbstractEasyPower {

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("ShadowmeldPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public ShadowmeldPower(AbstractCreature owner, int amount) {
        super(makeID("ShadowmeldPower"), NAME, AbstractPower.PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            amount--;
            if (amount == 0) {
                this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("ShadowmeldPower")));
            }
        }

    }

    @Override
    public int onPlayerGainedBlock(int blockAmount) {

        return blockAmount * 2;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}