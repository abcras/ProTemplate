package sts2silent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.cards.greenerCards.Shiv2;

public class StormOfSteelAction extends AbstractGameAction {
    private boolean upgrade;

    public StormOfSteelAction(boolean upgraded) {
        this.upgrade = upgraded;
    }

    public void update() {
        int theSize = AbstractDungeon.player.hand.size();
        if (this.upgrade) {
            AbstractCard s = (new Shiv2()).makeCopy();
            s.upgrade();
            this.addToTop(new MakeTempCardInHandAction(s, theSize));
        } else {
            this.addToTop(new MakeTempCardInHandAction(new Shiv2(), theSize));
        }

        this.addToTop(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, theSize, false));
        this.isDone = true;
    }
}