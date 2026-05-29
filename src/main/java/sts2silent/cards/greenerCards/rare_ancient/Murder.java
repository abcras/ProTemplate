package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.patches.Patches;

import static sts2silent.ModFile.makeID;

public class Murder extends AbstractEasyCard {
    public final static String ID = makeID("Murder");
    // intellij stuff attack, enemy, rare, 1, , , , 1, 

    public Murder() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 1;
        baseMagicNumber = magicNumber = 1;
    }


    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * Patches.totalCardsDrawnThisCombat;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * Patches.totalCardsDrawnThisCombat;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.damage += this.magicNumber;
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY, true));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}