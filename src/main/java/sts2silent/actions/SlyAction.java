package sts2silent.actions;

import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SlyAction extends UseCardAction {
    private final AbstractCard targetCard;
    public AbstractCreature target;
    public boolean exhaustCard;
    //public boolean returnToHand;
    public boolean reboundCard;
    private static final float DUR = 0.15F;

    public SlyAction(AbstractCard card, AbstractCreature target) {
        super(card, target);
        this.reboundCard = false;
        this.targetCard = card;
        actionType = ActionType.USE;

        this.setValues(AbstractDungeon.player, target, 1);
        this.duration = 0.15F;
    }

    public SlyAction(AbstractCard targetCard) {
        this(targetCard, (AbstractCreature) null);
    }

    public void update() {
        if (this.duration == 0.15F && !this.isDone) {
            System.out.println("From UseCardFromDiscardAction");
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (!this.targetCard.dontTriggerOnUseCard) {
                    //targetCard.dontTriggerOnUseCard = true;
                    targetCard.dontTriggerOnUseCard = true;
                    p.onAfterUseCard(this.targetCard, this);
                }
            }

            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                for (AbstractPower p : m.powers) {
                    if (!this.targetCard.dontTriggerOnUseCard) {
                        targetCard.dontTriggerOnUseCard = true;
                        p.onAfterUseCard(this.targetCard, this);
                    }
                }
            }

            this.targetCard.freeToPlayOnce = false;
            this.targetCard.isInAutoplay = false;
            if (this.targetCard.purgeOnUse) {
                this.addToTop(new ShowCardAndPoofAction(this.targetCard));
                this.isDone = true;
                AbstractDungeon.player.cardInUse = null;
                return;
            }

            if (this.targetCard.type == AbstractCard.CardType.POWER) {
                this.addToTop(new ShowCardAction(this.targetCard));
                if (Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(0.1F));
                } else {
                    this.addToTop(new WaitAction(0.7F));
                }

                AbstractDungeon.player.discardPile.empower(this.targetCard);
                this.isDone = true;
                AbstractDungeon.player.hand.applyPowers();
                AbstractDungeon.player.hand.glowCheck();
                AbstractDungeon.player.cardInUse = null;
                return;
            }

            AbstractDungeon.player.cardInUse = null;
            boolean spoonProc = false;
            if (this.exhaustCard && AbstractDungeon.player.hasRelic("Strange Spoon") && this.targetCard.type != AbstractCard.CardType.POWER) {
                spoonProc = AbstractDungeon.cardRandomRng.randomBoolean();
            }

            if (this.exhaustCard && !spoonProc) {
                AbstractDungeon.player.discardPile.moveToExhaustPile(this.targetCard);
                CardCrawlGame.dungeon.checkForPactAchievement();
            } else {
                if (spoonProc) {
                    AbstractDungeon.player.getRelic("Strange Spoon").flash();
                }

                if (this.reboundCard) {
                    AbstractDungeon.player.discardPile.moveToDeck(this.targetCard, false);
                } else if (this.targetCard.shuffleBackIntoDrawPile) {
                    AbstractDungeon.player.discardPile.moveToDeck(this.targetCard, true);
                } else if (this.targetCard.returnToHand) {
                    AbstractDungeon.player.discardPile.moveToHand(this.targetCard);
                    AbstractDungeon.player.onCardDrawOrDiscard();
                } else {
                    //AbstractDungeon.player.discardPile.moveToDiscardPile(this.targetCard);
                }
            }

            this.targetCard.exhaustOnUseOnce = false;
            this.targetCard.dontTriggerOnUseCard = false;
            this.addToBot(new HandCheckAction());
        }

        this.tickDuration();
    }
}
