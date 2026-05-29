package sts2silent.cards.greenerCards.common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.DaggerSprayEffect;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;

public class DaggerSpray extends AbstractEasyCard {
    public final static String ID = makeID("DaggerSpray");
    // intellij stuff ATTACK, ALLENEMIES, COMMON, 4, 2, , , , 

    public DaggerSpray() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        //Unnecessary
        color = Greener;
        baseDamage = 4;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0F));
        allDmg(AbstractGameAction.AttackEffect.NONE);

        /*this.addToBot(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new VFXAction(new DaggerSprayEffect(AbstractDungeon.getMonsters().shouldFlipVfx()), 0.0F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));*/
    }

    public void upp() {
        upgradeDamage(2);
    }
}