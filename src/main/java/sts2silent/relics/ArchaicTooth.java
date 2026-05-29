package sts2silent.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import sts2silent.CharacterFile;
import sts2silent.cards.greenerCards.base.Neutralize;
import sts2silent.cards.greenerCards.rare_ancient.Suppress;

import static sts2silent.ModFile.makeID;

public class ArchaicTooth extends AbstractEasyRelic {
    public static final String ID = makeID("ArchaicTooth");

    //This is Supress.

    public ArchaicTooth() {
        super(ID, RelicTier.BOSS, LandingSound.CLINK, CharacterFile.Enums.Greener);
    }

    @Override
    public void onEquip() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        AbstractCard suppress = new Suppress();


        AbstractCard neut = AbstractDungeon.player.masterDeck.findCardById(Neutralize.ID);
        if (neut != null) {
            AbstractDungeon.player.masterDeck.removeCard(neut);
            if (neut.upgraded)
                suppress.upgrade();
        }

        UnlockTracker.markCardAsSeen(suppress.cardID);
        group.addToBottom(suppress);
        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, this.DESCRIPTIONS[1]);

        //CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
    }


    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.masterDeck.findCardById(Neutralize.ID) != null;
    }

}
