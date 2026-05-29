package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.actions.unique.SkewerAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class Skewer extends AbstractEasyCard {
    public final static String ID = makeID("Skewer");
    // intellij stuff attack, enemy, uncommon, 8, 3, , , , 

    public Skewer() {
        super(ID, -1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SkewerAction(p, m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        upgradeDamage(3);

    }
}