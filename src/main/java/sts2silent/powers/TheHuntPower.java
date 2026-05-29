package sts2silent.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rewards.RewardItem;
import sts2silent.patches.Patches;

import static sts2silent.ModFile.makeID;

public class TheHuntPower extends AbstractEasyPower {

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static AbstractMonster monsterThatWasKilled;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("TheHuntPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public TheHuntPower(AbstractCreature owner, AbstractMonster monster) {
        super(makeID("TheHuntPower"), NAME, AbstractPower.PowerType.BUFF, false, owner, 1);
        monsterThatWasKilled = monster;
    }

    @Override
    public void onVictory() {
        super.onVictory();
        this.flashWithoutSound();

        //Extra card reward is handled in CombatRewardScreen in setupItemReward() due to loading issues with other implementations
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] + amount +DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + amount +DESCRIPTIONS[2];
        }
    }
}