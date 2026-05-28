package sts2silent.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static sts2silent.ModFile.makeID;

public class OutbreakPower extends AbstractEasyPower {

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("OutbreakPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public OutbreakPower(AbstractCreature owner, int damageAmount) {
        super(makeID("OutbreakPower"), NAME, PowerType.BUFF, false, owner, damageAmount);
        amount2 = 3;
        isTwoAmount = true;
        //DamageToDealOnTrigger = damageAmount;
        updateDescription();
        //this.
        /*for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if (Objects.equals(c.cardID, Shiv2.ID)) {
                increaseDamage = false;
                break;
            }
        }*/

    }

    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(PoisonPower.POWER_ID) && source == this.owner && !target.hasPower("Artifact")) {
            //this.flash();
            //amount2
            reducePowerOfTwo(1);
            if (amount2 == 0) {
                this.flashWithoutSound();
                this.addToBot(new DamageAllEnemiesAction((AbstractPlayer) owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.POISON));
                amount2 = 3;
            }
        }

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }


    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        amount += stackAmount;
        updateDescription();
        //this.amount += stackAmount;
        //this.updateExistingShivs();
    }
}