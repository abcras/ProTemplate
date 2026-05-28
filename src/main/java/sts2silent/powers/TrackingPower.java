package sts2silent.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.DamageModApplyingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.WeakPower;


import java.util.Collections;
import java.util.List;

import static sts2silent.ModFile.makeID;

public class TrackingPower  extends AbstractEasyPower /*implements DamageModApplyingPower */{

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("TrackingPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public TrackingPower(AbstractCreature owner, int amount) {
        super(makeID("TrackingPower"), NAME, PowerType.BUFF, false, owner, amount);
    }


    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {

        //info.

        System.out.println("test in onAttackToChangeDamage for tracking");
        return super.onAttackToChangeDamage(info, damageAmount);
    }

    AbstractCreature lastTarget = null;

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        lastTarget = target;
        //info.type
        /*if (info.type == DamageInfo.DamageType.NORMAL) {
            return this.owner != null && !this.owner.isPlayer &&
                    AbstractDungeon.player.hasPower(makeID("TrackingPower"))
                    ? damageAmount * AbstractDungeon.player.getPower(makeID("TrackingPower")).amount
                    : damageAmount * 1;
        } else {
            return damageAmount;
        }*/
        System.out.println("test in on attack for tracking");
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if(lastTarget != null)
        {
            float expectedDamage = lastTarget.hasPower(WeakPower.POWER_ID) ? damage * AbstractDungeon.player.getPower(makeID("TrackingPower")).amount
                    : damage;
            lastTarget = null;
            return expectedDamage;
        }
        /*card.*/

        return damage;


    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount-1);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    /*@Override
    public void onAddedDamageModsToDamageInfo(DamageInfo info, Object instigator) {
        DamageModApplyingPower.super.onAddedDamageModsToDamageInfo(info, instigator);
    }

    @Override
    public boolean shouldPushMods(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        return false;
    }

    @Override
    public List<AbstractDamageModifier> modsToPush(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        return Collections.emptyList();
    }*/
}