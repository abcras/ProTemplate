package sts2silent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayTopCardFromLimbo extends AbstractGameAction {

    public PlayTopCardFromLimbo(AbstractCreature target) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.limbo.isEmpty()) {
                this.isDone = true;
                return;
            }

            if (!AbstractDungeon.player.limbo.isEmpty()) {
                AbstractCard card = AbstractDungeon.player.limbo.getTopCard();
                AbstractDungeon.player.limbo.group.remove(card);
                AbstractDungeon.getCurrRoom().souls.remove(card);

                card.current_y = -200.0F * Settings.scale;
                card.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                card.target_y = (float) Settings.HEIGHT / 2.0F;
                card.targetAngle = 0.0F;
                card.lighten(false);
                card.drawScale = 0.12F;
                card.targetDrawScale = 0.75F;
                card.applyPowers();

                this.addToTop(new NewQueueCardAction(card, this.target, false, true));
                this.addToTop(new UnlimboAction(card));

                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));

            }

            this.isDone = true;
        }

    }
}
