package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Expose extends AbstractEasyCard {
    public final static String ID = makeID("Expose");
    // intellij stuff skill, enemy, uncommon, , , , , 2, 1

    public Expose() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(ArtifactPower.POWER_ID)) {
            removePower(m.getPower(ArtifactPower.POWER_ID));
        }
        m.loseBlock();
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}