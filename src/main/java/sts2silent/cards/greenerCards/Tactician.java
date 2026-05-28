package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import sts2silent.patches.Patches;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Tactician extends AbstractEasyCard {
    public final static String ID = makeID("Tactician");
    // intellij stuff skill, self, uncommon, , , , , 1, 1

    public Tactician() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        Patches.SlyField.sly.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}