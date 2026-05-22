/*
package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.ModFile;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class FollowThrough_V01050 extends AbstractEasyCard {
    public final static String ID = makeID("FollowThrough");
    // intellij stuff attack, enemy, common, 7, 2, , , , 

    public FollowThrough_V01050() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        //this might need to be > 6 depending on if
        if (p.hand.size() > 5) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();

        if (AbstractDungeon.player.hand.size() > 6) {

            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }

    }

    public void upp() {
        upgradeDamage(2);

    }
}*/
