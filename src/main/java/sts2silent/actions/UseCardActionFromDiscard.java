package sts2silent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class UseCardActionFromDiscard extends AbstractGameAction {
    private final AbstractCard targetCard;
    public AbstractCreature target;
    public boolean exhaustCard;
    //public boolean returnToHand;
    public boolean reboundCard;
    private static final float DUR = 0.15F;

    public UseCardActionFromDiscard(AbstractCard card, AbstractCreature target) {
        this.target = null;
        this.reboundCard = false;
        this.targetCard = card;
        this.target = target;
        if (card.exhaustOnUseOnce || card.exhaust) {
            this.exhaustCard = true;
        }

        this.setValues(AbstractDungeon.player, (AbstractCreature)null, 1);
        this.duration = 0.15F;

        for(AbstractPower p : AbstractDungeon.player.powers) {
            if (!card.dontTriggerOnUseCard) {
                p.onUseCard(card, new UseCardAction(targetCard, target));
            }
        }

        for(AbstractRelic r : AbstractDungeon.player.relics) {
            if (!card.dontTriggerOnUseCard) {
                r.onUseCard(card, new UseCardAction(targetCard, target));
            }
        }

        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!card.dontTriggerOnUseCard) {
                c.triggerOnCardPlayed(card);
            }
        }

        for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (!card.dontTriggerOnUseCard) {
                c.triggerOnCardPlayed(card);
            }
        }

        for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (!card.dontTriggerOnUseCard) {
                c.triggerOnCardPlayed(card);
            }
        }

        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            for(AbstractPower p : m.powers) {
                if (!card.dontTriggerOnUseCard) {
                    p.onUseCard(card, new UseCardAction(targetCard, target));
                }
            }
        }

        if (this.exhaustCard) {
            this.actionType = ActionType.EXHAUST;
        } else {
            this.actionType = ActionType.USE;
        }

    }

    public UseCardActionFromDiscard(AbstractCard targetCard) {
        this(targetCard, (AbstractCreature)null);
    }

    public void update() {
        if (this.duration == 0.15F) {
            for(AbstractPower p : AbstractDungeon.player.powers) {
                if (!this.targetCard.dontTriggerOnUseCard) {
                    p.onAfterUseCard(this.targetCard, new UseCardAction(targetCard, target));
                }
            }

            for(AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                for(AbstractPower p : m.powers) {
                    if (!this.targetCard.dontTriggerOnUseCard) {
                        p.onAfterUseCard(this.targetCard, new UseCardAction(targetCard, target));
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
