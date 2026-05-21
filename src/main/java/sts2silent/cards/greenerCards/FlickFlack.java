package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class FlickFlack extends AbstractEasyCard {
    public final static String ID = makeID("Flick-Flack");
    // intellij stuff attack, all_monsters, common, 6, 2, , , , 

    public FlickFlack() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        isMultiDamage = true;
    }

    public void DoStuff(AbstractPlayer p){
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        DoStuff(p);
    }

    @Override
    public void triggerOnManualDiscard() {
        DoStuff(AbstractDungeon.player);
    }

    public void upp() {
        upgradeDamage(2);

    }
}