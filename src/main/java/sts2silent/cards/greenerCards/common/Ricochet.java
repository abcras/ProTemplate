package sts2silent.cards.greenerCards.common;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.patches.Patches;

import static sts2silent.ModFile.makeID;

public class Ricochet extends AbstractEasyCard {
    public final static String ID = makeID("Ricochet");
    // intellij stuff attack, random, common, 3, , , , 4, 1

    public Ricochet() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 4;
        Patches.SlyField.sly.set(this, true);
    }

    AbstractGameAction.AttackEffect[] ran = new AbstractGameAction.AttackEffect[]{
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL,
            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL,
            AbstractGameAction.AttackEffect.SLASH_VERTICAL
    };

    public void DoStuff(){
        for (int i = 0; i < magicNumber; i++) {
            dmgRandom(ran[AbstractDungeon.cardRandomRng.random(2)]);
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        DoStuff();
    }

    /*@Override
    public void triggerOnManualDiscard() {
        DoStuff();
    }*/

    public void upp() {
        upgradeMagicNumber(1);

    }
}