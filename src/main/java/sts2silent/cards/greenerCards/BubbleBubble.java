package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class BubbleBubble extends AbstractEasyCard {
    public final static String ID = makeID("BubbleBubble");
    // intellij stuff skill, monster, uncommon, , , , , 9, 3

    public BubbleBubble() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(m.hasPower(PoisonPower.NAME)){
            applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(3);

    }
}