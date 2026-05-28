package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.unique.FlechetteAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Flechettes extends AbstractEasyCard {
    public final static String ID = makeID("Flechettes");
    // intellij stuff attack, enemy, uncommon, 5, 2, , , , 

    public Flechettes() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FlechetteAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;

        for(AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.SKILL) {
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

    public void upp() {
        upgradeDamage(2);

    }
}