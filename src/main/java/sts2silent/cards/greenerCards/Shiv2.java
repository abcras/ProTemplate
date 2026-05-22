package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Shiv2 extends AbstractEasyCard {
    public final static String ID = makeID("Shiv");
    // intellij stuff attack, monster, common, 4, 2, , , ,
    boolean inky = false;
    int newBaseDamage = 4;

    public Shiv2() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = newBaseDamage = 4;
        this.exhaust = true;
        updateWithPowers();
    }

    public Shiv2(boolean inkyEnhancement) {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.exhaust = true;
        if (inkyEnhancement) {
            baseDamage = newBaseDamage = 6;
            this.inky = true;

            this.name = "Inky " + this.name;
            magicNumber = 1;
        }
        updateWithPowers();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (isMultiDamage) {
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

            if (inky) {
                for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        } else {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

            if (inky) {
                this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }

    public void updateWithPowers() {
        //Update the shiv when the powers are displayed.
        //Phantom blades dmg buff is handled like vigor
        if (AbstractDungeon.player == null) {
            return;
        }
        StringBuilder newDescriptionBuilder = new StringBuilder();

        //newDescriptionBuilder.

        //cardStrings.EXTENDED_DESCRIPTION[0]
        if (AbstractDungeon.player.hasPower("Accuracy")) {
            baseDamage = newBaseDamage + AbstractDungeon.player.getPower("Accuracy").amount;
        } else {
            baseDamage = newBaseDamage;
        }

        //update the visuals of the card
        if (AbstractDungeon.player.hasPower(makeID("PhantomBladesPower"))) {
            this.selfRetain = true;
            newDescriptionBuilder.append(cardStrings.EXTENDED_DESCRIPTION[0]);
        }
        newDescriptionBuilder.append(cardStrings.EXTENDED_DESCRIPTION[1]);

        if (AbstractDungeon.player.hasPower(makeID("FanOfKnivesPower"))) {
            this.target = CardTarget.ALL_ENEMY;
            this.isMultiDamage = true;
            newDescriptionBuilder.append(cardStrings.EXTENDED_DESCRIPTION[3]);
        } else {
            newDescriptionBuilder.append(cardStrings.EXTENDED_DESCRIPTION[2]);
        }

        if (inky) {
            newDescriptionBuilder.append(cardStrings.EXTENDED_DESCRIPTION[4]);
        }

        newDescriptionBuilder.append(cardStrings.EXTENDED_DESCRIPTION[5]);

        rawDescription = newDescriptionBuilder.toString();

        initializeTitle();
        initializeDescription();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard temp = super.makeStatEquivalentCopy();
        ((Shiv2) temp).updateWithPowers();


        return temp;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shiv2(this.inky);
    }

    @Override
    protected void upgradeDamage(int amount) {
        super.upgradeDamage(amount);
        newBaseDamage += amount;
    }

    public void upp() {
        upgradeDamage(2);

    }
}