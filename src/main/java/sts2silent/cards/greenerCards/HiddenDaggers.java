package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class HiddenDaggers extends AbstractEasyCard {
    public final static String ID = makeID("HiddenDaggers");
    // intellij stuff skill, self, uncommon, , , , , 2, 

    public HiddenDaggers() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        discard(magicNumber);
        if(!upgraded){
            makeInHand(new Shiv2(), magicNumber);
        } else {
            AbstractEasyCard c = new Shiv2();
            c.upgrade();
            makeInHand(c, magicNumber);
        }
    }

    public void upp() {

    }
}