package sts2silent.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import sts2silent.patches.Patches;

import static sts2silent.ModFile.makeID;

public class SpeedsterPower extends AbstractEasyPower {

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("SpeedsterPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public SpeedsterPower(AbstractCreature owner, int amount) {
        super(makeID("SpeedsterPower"), NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        super.onCardDraw(card);
        this.flashWithoutSound();
        //this.flash();
        this.addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}