package sts2silent.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static sts2silent.ModFile.makeID;

public class AccelerantPower extends AbstractEasyPower {
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(makeID("AccelerantPower"));
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public AccelerantPower(AbstractCreature owner, int amount) {
        super(makeID("AccelerantPower"), NAME, AbstractPower.PowerType.BUFF, false, owner, amount);
    }

    /*@Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {

            this.flashWithoutSound();
            for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {

                this.addToBot(new PoisonLoseHpAction(mon, this.owner, mon, AbstractGameAction.AttackEffect.POISON));
            }
        }
    }*/

    /*  @Override
      public void atStartOfTurn() {

      }
  */
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] +amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] +amount + DESCRIPTIONS[2];
        }
    }
}
