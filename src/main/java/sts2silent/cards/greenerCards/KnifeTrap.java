package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.unique.FlechetteAction;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.actions.KnifeTrapAction;
import sts2silent.actions.PlayTopCardFromLimbo;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import java.util.Collection;
import java.util.Objects;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class KnifeTrap extends AbstractEasyCard {
    public final static String ID = makeID("KnifeTrap");
    // intellij stuff skill, enemy, rare, , , , , , 

    public KnifeTrap() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;

        for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (Objects.equals(c.cardID, Shiv2.ID)) {
                ++count;
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        //CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        //figure out why the KNife trap action doesn't display the shiv cards when they are played.

        //atb(new KnifeTrapAction(m, this.upgraded));

        /*for (int i = 0; i < p.exhaustPile.size(); i++) {
            AbstractCard c = p.exhaustPile.group.get(i);
            if (Objects.equals(c.cardID, Shiv2.ID) || Objects.equals(c.cardID, Shiv.ID)) {
                if (this.upgraded) {
                    c.upgrade();
                }
                p.drawPile.addToTop(c);
                p.exhaustPile.removeCard(c);
                i--;
                //c.exhaustOnUseOnce = true;
                //c.use(p, m);
                atb(new PlayTopCardAction(m,  true));
            }
        }*/


        for (int i = 0; i < p.exhaustPile.size(); i++) {
            AbstractCard c = p.exhaustPile.group.get(i);
            if (Objects.equals(c.cardID, Shiv2.ID) || Objects.equals(c.cardID, Shiv.ID)) {
                if (this.upgraded) {
                    c.upgrade();
                }
                //thank you to Substitute on the sts discord for help finding the unfadeOut() function
                c.unfadeOut();
                p.limbo.addToTop(c);
                p.exhaustPile.removeCard(c);
                i--;
                //c.exhaustOnUseOnce = true;
                //c.use(p, m);
                atb(new PlayTopCardFromLimbo(m));
            }
        }

        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();

        /*

        CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (Objects.equals(c.cardID, Shiv2.ID) || Objects.equals(c.cardID, Shiv.ID)) {
                if (this.upgraded) {
                    c.upgrade();
                }
                temp.addToTop(c);
                //c.exhaustOnUseOnce = true;
                //c.use(p, m);
            }
        }
        for (AbstractCard c : temp.group) {
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
            this.addToTop(new NewQueueCardAction(c, m, false, true));
            this.addToTop(new UnlimboAction(c));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
        }
*/

        //this.addToTop(new NewQueueCardAction(card, m, false, true));


        //p.exhaustPile.remove
        //p.exhaustPile.group.removeAll((Collection<AbstractCard>) temp);

    }

    public void upp() {

    }
}
