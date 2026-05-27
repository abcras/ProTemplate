package sts2silent.actions;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import sts2silent.patches.SlyPatch;

public class AddSlyAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final boolean ForTheTurn;
    private AbstractPlayer p;

    public AddSlyAction(int amount, boolean forTheTurn) {
        this.ForTheTurn = forTheTurn;
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroupType.UNSPECIFIED);

            for (AbstractCard c : this.p.hand.group) {
                if (c.type == CardType.SKILL && !(SlyPatch.SlyField.slyForTurn.get(c) || SlyPatch.SlyField.sly.get(c))) {
                    tmp.addToTop(c);
                }
            }

            if (tmp.isEmpty()) {
                this.isDone = true;
            } else if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    //this.p.drawPile.removeCard(card);
                    //AbstractDungeon.player.hand.addToTop(card);
                    if (ForTheTurn) {
                        SlyPatch.SlyField.slyForTurn.set(card, true);
                    } else {
                        SlyPatch.SlyField.sly.set(card, true);
                    }
                    card.initializeDescription();
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }

                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, "Add Sly to any skill", false);
                this.tickDuration();
            }
        } else {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    c.unhover();

                    if (ForTheTurn) {
                        SlyPatch.SlyField.slyForTurn.set(c, true);
                    } else {
                        SlyPatch.SlyField.sly.set(c, true);
                    }
                    c.initializeDescription();
                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("AttackFromDeckToHandAction");
        TEXT = uiStrings.TEXT;
    }
}
