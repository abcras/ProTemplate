package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class Pinpoint extends AbstractEasyCard {
    public final static String ID = makeID("Pinpoint");
    // intellij stuff attack, enemy, uncommon, 15, 4, , , , 

    public Pinpoint() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if(c.type == CardType.SKILL){
            this.setCostForTurn(this.costForTurn - 1);
        }
    }

    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        calculateCost();
    }

    public void calculateCost(){
        AbstractDungeon.actionManager.cardsPlayedThisTurn
                .forEach( z -> {
                    if(z.type == CardType.SKILL)
                    {

                        this.setCostForTurn(this.costForTurn - 1);
                    }
                } );
    }

    public void atTurnStart() {
        this.resetAttributes();
        this.applyPowers();
    }


    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }

    }

    public AbstractCard makeCopy() {
        AbstractCard tmp = new Pinpoint();
        if (CardCrawlGame.dungeon != null && AbstractDungeon.currMapNode != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            this.calculateCost();
        }

        return tmp;
    }

    public void upp() {
        upgradeDamage(4);

    }
}