package sts2silent.patches;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
import javassist.*;
import sts2silent.actions.UseCardActionFromDiscard;
import javassist.expr.ExprEditor;

import java.util.Objects;

import static sts2silent.ModFile.makeID;
import static sts2silent.ModFile.modID;

public class Patches {

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class SlyField {
        public static SpireField<Boolean> sly = new SpireField<>(() -> false);
        public static SpireField<Boolean> slyForTurn = new SpireField<>(() -> false);
    }

    @SpirePatch(clz = AbstractCard.class, method = "resetAttributes")
    public static class resetSlyGlowColorInResetAttributesPatch{
        public static void Postfix(AbstractCard __instance) {
            if (SlyField.slyForTurn.get(__instance) || SlyField.sly.get(__instance)) {
                /*SlyField.slyForTurn.set(__instance, false);
                __instance.initializeDescription();*/
                __instance.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "freeToPlay")
    public static class extendFreeToPlayMethod{
        public static boolean Postfix(boolean __result, AbstractCard __instance) {
            boolean res = AbstractDungeon.player != null &&
                    AbstractDungeon.currMapNode != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    AbstractDungeon.player.hasPower(makeID("FreeSkillPower"))
                    && __instance.type == AbstractCard.CardType.SKILL;

            return res || __result;
        }
    }


    /*@SpireInstrumentPatch
    public static ExprEditor Foobar()
    {
        return new ExprEditor() {
            CtClass point;

            {
                try {
                    point = ClassPool.getDefault().get("WeakPower");
                } catch (NotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            CtMethod m;

            {
                try {
                    m = CtNewMethod.make(
                            "public float atDamageReceive(float damage, DamageInfo.DamageType type) " +
                                    "{ " +
                                    "return __instance.owner != null && !__instance.owner.isPlayer &&" +
                                    "AbstractDungeon.player.hasPower(makeID(\"TrackingPower\"))"+
                                    "? __damage * AbstractDungeon.player.getPower(makeID(\"TrackingPower\")).amount"+
                                    ": __damage * 1;"+
                                    "}",
                            point);
                } catch (CannotCompileException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }*/

    @SpirePatch(clz = AbstractPower.class, method = "atDamageReceive", paramtypez = {float.class, DamageInfo.DamageType.class})
    public static class extendWeakPowerToWorkWithTrackingMethod{

        @SpirePostfixPatch
        public static float Postfix(AbstractPower __instance, float __damage, DamageInfo.DamageType __type) {

            if(!Objects.equals(__instance.ID, WeakPower.POWER_ID))
            {
                return __damage;
            }
            if (__type == DamageInfo.DamageType.NORMAL) {
                    return __instance.owner != null && !__instance.owner.isPlayer &&
                            AbstractDungeon.player.hasPower(makeID("TrackingPower"))
                            ? __damage * AbstractDungeon.player.getPower(makeID("TrackingPower")).amount
                            : __damage * 1;
            } else {
                return __damage;
            }
        }
       /* public static float Replace(float __damage, DamageInfo.DamageType __type, WeakPower __instance) {
            if (__type == DamageInfo.DamageType.NORMAL) {
                return __instance.owner != null && !__instance.owner.isPlayer &&
                        AbstractDungeon.player.hasPower(makeID("TrackingPower"))
                        ? __damage * AbstractDungeon.player.getPower(makeID("TrackingPower")).amount
                        : __damage * 1;
            } else {
                return __damage;
            }
        }*/
    }


    /*public void applyStartOfTurnCards() {
        for(AbstractCard c : this.drawPile.group) {
            if (c != null) {
                c.atTurnStart();
            }
        }

        for(AbstractCard c : this.hand.group) {
            if (c != null) {
                c.atTurnStart();
            }
        }

        for(AbstractCard c : this.discardPile.group) {
            if (c != null) {
                c.atTurnStart();
            }
        }

    }*/
    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnCards")
    public static class TriggerStartOfTurnForExhaustPilePatch{
        //Experimental, should not fuck with anything, but fix an issue with sly getting added to a card,
        // and it is then exhausted and then brought back during another turn with Exhume (SUPER NICHE)
        public static void Postfix(AbstractPlayer __instance) {

            for(AbstractCard c : __instance.exhaustPile.group) {
                if (c != null) {
                    c.atTurnStart();
                }
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "atTurnStart")
    public static class resetSlyForTurnAtStartOfTurnPatch{
        public static void Postfix(AbstractCard __instance) {
            if (SlyField.slyForTurn.get(__instance)) {
                SlyField.slyForTurn.set(__instance, false);
                __instance.initializeDescription();
                //__instance.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
            }
        }
    }

    @SpirePatch(clz = HandCardSelectScreen.class, method = "updateHand")
    public static class makeSlyCardsGlowDuringDiscardSelectionPatch {
        public static void Postfix(HandCardSelectScreen __instance, CardGroup ___hand, boolean ___forTransform, boolean ___forUpgrade) {

            if(___forTransform || ___forUpgrade)
            {
                return;
            }
            //if()
            for(AbstractCard c : ___hand.group) {
                if ((SlyField.slyForTurn.get(c) || SlyField.sly.get(c)) && !c.isGlowing) {
                    c.glowColor = Color.GOLD.cpy();
                    c.beginGlowing();
                }
            }
        }
    }
    /*@SpirePatch(clz = AbstractCard.class, method = "triggerOnEndOfPlayerTurn")
    public static class clearSlyForTurnOnPlayerTurnEndPatch {
        public static void Postfix(AbstractCard __instance) {

            //boolean temp = SlyField.slyForTurn.get(__instance);

            if (SlyField.slyForTurn.get(__instance)) {
                SlyField.slyForTurn.set(__instance, false);
                __instance.initializeDescription();
            }
        }
    }*/

    @SpirePatch(clz = AbstractCard.class, method = "triggerOnManualDiscard")
    public static class fixManualDiscardPatcher {
        public static void Postfix(AbstractCard __instance) {

            if (SlyField.sly.get(__instance) || SlyField.slyForTurn.get(__instance)) {
                AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (!m.isDead && !m.escaped) {
                    if (!AbstractDungeon.player.endTurnQueued) {
                        __instance.use(AbstractDungeon.player, m);

                        AbstractDungeon.actionManager.addToBottom(new UseCardActionFromDiscard(__instance, m));
                    }
                }
            }

        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "initializeDescription")
    public static class addSlipKeyWordToDescriptionPatch {
        public static void Prefix(AbstractCard __instance) {

            if (SlyField.sly.get(__instance) || SlyField.slyForTurn.get(__instance)) {

                if (!__instance.rawDescription.contains(modID + ":Sly. NL")) {
                    __instance.rawDescription = modID + ":Sly. NL " + __instance.rawDescription;
                }
                /*AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (!m.isDead && !m.escaped) {
                    if (!AbstractDungeon.player.endTurnQueued) {
                        __instance.use(AbstractDungeon.player, m);

                        AbstractDungeon.actionManager.addToBottom(new UseCardActionFromDiscard(__instance, m));
                    }
                }*/
            } else if (!SlyField.slyForTurn.get(__instance) && !SlyField.sly.get(__instance)) {
                if (__instance.rawDescription.contains(modID + ":Sly. NL")) {
                    __instance.rawDescription = __instance.rawDescription.replace(modID + ":Sly. NL", "");
                }
            }

        }
    }
}