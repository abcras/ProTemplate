package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class MementoMori extends AbstractEasyCard {
    public final static String ID = makeID("MementoMori");
    // intellij stuff attack, enemy, uncommon, 9, 2, , , 4, 1

    public MementoMori() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 9;
        baseMagicNumber = magicNumber = 4;
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * GameActionManager.totalDiscardedThisTurn;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber * GameActionManager.totalDiscardedThisTurn;
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
        upgradeDamage(2);
        upgradeMagicNumber(1);

    }
}