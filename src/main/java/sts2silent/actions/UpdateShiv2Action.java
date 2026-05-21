package sts2silent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import sts2silent.cards.greenerCards.EchoingSlash;
import sts2silent.cards.greenerCards.Shiv2;

public class UpdateShiv2Action extends AbstractGameAction {


    public UpdateShiv2Action() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.POWER;
        this.source = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            isDone = true;

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof Shiv2) {
                    ((Shiv2) c).updateWithPowers();
                }
            }

            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c instanceof Shiv2) {
                    ((Shiv2) c).updateWithPowers();
                }
            }

            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c instanceof Shiv2) {
                    ((Shiv2) c).updateWithPowers();
                }
            }

            for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                if (c instanceof Shiv2) {
                    ((Shiv2) c).updateWithPowers();
                }
            }

        }
        this.isDone = true;
    }

}
