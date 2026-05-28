package sts2silent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.cards.greenerCards.Shiv2;

import java.util.Objects;

public class KnifeTrapAction extends AbstractGameAction {
    private boolean upgradeShivs;
    CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public KnifeTrapAction(AbstractCreature target, boolean upgrade) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.upgradeShivs = upgrade;


        /*this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;*/
    }

    public void update() {


        /*if (this.duration == this.startDuration) {
            if (AbstractDungeon.player.exhaustPile.isEmpty() && AbstractDungeon.player.exhaustPile.findCardById(Shiv2.ID) == null) {
                this.isDone = true;
                return;
            } else {


                for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (Objects.equals(c.cardID, Shiv2.ID)) {
                        if (this.upgradeShivs) {
                            c.upgrade();
                        }
                        temp.addToTop(c);
                        //c.use(p, m);
                    }
                }

                //AbstractDungeon.gridSelectScreen.open(temp, 1, TEXT[0], false);
                this.tickDuration();
            }
        } else {
            if (!temp.isEmpty()) {
                for(AbstractCard c : temp.group) {
                    c.exhaust = true;
                    AbstractDungeon.player.exhaustPile.group.remove(c);
                    AbstractDungeon.getCurrRoom().souls.remove(c);
                    this.addToBot(new NewQueueCardAction(c, target, false, true));
                }

                //AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
        }*/


        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.exhaustPile.isEmpty() && AbstractDungeon.player.exhaustPile.findCardById(Shiv2.ID) == null) {
                this.isDone = true;
                return;
            }

            if (!AbstractDungeon.player.exhaustPile.isEmpty() && AbstractDungeon.player.exhaustPile.findCardById(Shiv2.ID) != null) {


                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (Objects.equals(c.cardID, Shiv2.ID) || Objects.equals(c.cardID, Shiv.ID)) {
                        if (this.upgradeShivs) {
                            c.upgrade();
                        }
                        c.unfadeOut();
                        AbstractDungeon.player.limbo.group.add(c);
                        c.exhaustOnUseOnce = true;
                        //c.use(p, m);
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.limbo.group) {
                    AbstractDungeon.player.exhaustPile.group.remove(c);
                    AbstractDungeon.getCurrRoom().souls.remove(c);
                    c.current_y = -200.0F * Settings.scale;
                    c.target_x = (float) Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
                    c.target_y = (float) Settings.HEIGHT / 2.0F;
                    c.targetAngle = 0.0F;
                    c.lighten(false);
                    c.drawScale = 0.12F;
                    c.targetDrawScale = 0.75F;
                    c.applyPowers();
                    //addToTop(new ShowCardAction(c));
                    this.addToTop(new NewQueueCardAction(c, this.target, false, true));
                    this.addToTop(new UnlimboAction(c));
                    if (!Settings.FAST_MODE) {
                        this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                    } else {
                        this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                    }
                }
            }
            this.isDone = true;
        }
    }
}