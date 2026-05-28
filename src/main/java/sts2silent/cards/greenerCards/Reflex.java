package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import sts2silent.patches.Patches;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Reflex extends AbstractEasyCard {
    public final static String ID = makeID("Reflex");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public Reflex() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        Patches.SlyField.sly.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        draw(magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}