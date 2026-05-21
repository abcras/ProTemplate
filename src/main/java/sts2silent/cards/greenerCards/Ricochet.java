package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Ricochet extends AbstractEasyCard {
    public final static String ID = makeID("Ricochet");
    // intellij stuff attack, random, common, 3, , , , 4, 1

    public Ricochet() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 4;
    }

    public void DoStuff(){
        for (int i = 0; i < magicNumber; i++) {
            dmgRandom(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        }
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        DoStuff();
    }

    @Override
    public void triggerOnManualDiscard() {
        DoStuff();
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}