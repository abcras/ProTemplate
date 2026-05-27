package sts2silent.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import sts2silent.actions.UpdateShiv2Action;
import sts2silent.cards.greenerCards.Shiv2;
import sts2silent.patches.SlyPatch;

import java.util.Objects;

import static sts2silent.ModFile.makeID;

public class MasterPlannerPower extends AbstractEasyPower {

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("MasterPlannerPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public MasterPlannerPower(AbstractCreature owner) {
        super(makeID("MasterPlannerPower"), NAME, PowerType.BUFF, false, owner, 1);
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            if (!SlyPatch.SlyField.sly.get(card)) {
                SlyPatch.SlyField.sly.set(card, true);
                card.initializeDescription();
                this.flash();
            }
            //this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Vigor"));
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}