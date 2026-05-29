package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class Mirage extends AbstractEasyCard {
    public final static String ID = makeID("Mirage");
    // intellij stuff skill, all_enemy, uncommon, , , , , , 

    public Mirage() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        exhaust = true;
        baseBlock = 0;
        //CalculatePoisonOnMonsters();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CalculatePoisonOnMonsters();
        blck();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        //int realBaseBlock = this.baseDamage;
        CalculatePoisonOnMonsters();
        super.applyPowers();

        this.initializeDescription();
        //this.isBlockModified = this.block != this.baseBlock;
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        CalculatePoisonOnMonsters();
        super.calculateCardDamage(mo);
    }

    private void CalculatePoisonOnMonsters(){
        baseBlock = 0;
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters){
            if(mon.hasPower(PoisonPower.POWER_ID))
            {
                baseBlock += mon.getPower(PoisonPower.POWER_ID).amount;
            }
        }
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}