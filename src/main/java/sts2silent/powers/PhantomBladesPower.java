package sts2silent.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.actions.UpdateShiv2Action;
import sts2silent.cards.greenerCards.Shiv2;

import java.util.Objects;

import static sts2silent.ModFile.makeID;

public class PhantomBladesPower extends AbstractEasyPower {

    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("PhantomBladesPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
    public PhantomBladesPower(AbstractCreature owner, int amount) {
        super(makeID("PhantomBladesPower"), NAME, PowerType.BUFF, false, owner, amount);


        for(AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            if (Objects.equals(c.cardID, Shiv2.ID)) {
                increaseDamage = false;
                break;
            }
        }
        this.addToBot(new UpdateShiv2Action());
    }
    boolean increaseDamage = true;

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        //this.updateExistingShivs();
    }
    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        //return super.atDamageGive(damage, type, card);
        if (increaseDamage && (Objects.equals(card.cardID, Shiv2.ID) || (Objects.equals(card.cardID, Shiv.ID)))) {
            //this.flash();
            //this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Vigor"));
            //this.addToBot();
            return type == DamageInfo.DamageType.NORMAL ? damage + (float) this.amount : damage;
        }
        return damage;
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurn();
        increaseDamage = true;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (increaseDamage && (Objects.equals(card.cardID, Shiv2.ID ) || (Objects.equals(card.cardID, Shiv.ID)))) {
            this.flash();
            increaseDamage = false;
            //this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "Vigor"));
        }

    }
    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}