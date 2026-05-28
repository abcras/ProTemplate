package sts2silent.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.WeakPower;

import static sts2silent.ModFile.makeID;

public class TrackingEnemyPower  extends AbstractEasyPower implements InvisiblePower /*implements DamageModApplyingPower */{

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("TrackingPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public TrackingEnemyPower(AbstractCreature owner) {
        super(makeID("TrackingEnemyPower"), NAME, PowerType.BUFF, false, owner, 1);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {

        float expectedDamage = owner.hasPower(WeakPower.POWER_ID) ? damage * AbstractDungeon.player.getPower(makeID("TrackingPower")).amount
                : damage;

        return super.atDamageReceive(expectedDamage, damageType, card);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}