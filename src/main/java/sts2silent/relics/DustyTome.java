package sts2silent.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import sts2silent.CharacterFile;
import sts2silent.cards.greenerCards.Neutralize;
import sts2silent.cards.greenerCards.Suppress;
import sts2silent.cards.greenerCards.WraithForm;

import static sts2silent.ModFile.makeID;

public class DustyTome extends AbstractEasyRelic {
    public static final String ID = makeID("DustyTome");

    //Wraith Form
    public DustyTome() {
        super(ID, RelicTier.BOSS, LandingSound.HEAVY, CharacterFile.Enums.Greener);
    }


    @Override
    public void onEquip() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractCard c = new WraithForm();

        UnlockTracker.markCardAsSeen(c.cardID);
        c.upgrade();
        group.addToBottom(c);
        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);

        //CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
    }

}
