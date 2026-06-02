package sts2silent.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;
import javassist.*;
import sts2silent.actions.SlyAction;
import sts2silent.util.HuntCardReward;

import java.util.ArrayList;
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
    public static class resetSlyGlowColorInResetAttributesPatch {
        public static void Postfix(AbstractCard __instance) {
            if (SlyField.slyForTurn.get(__instance) || SlyField.sly.get(__instance)) {
                /*SlyField.slyForTurn.set(__instance, false);
                __instance.initializeDescription();*/
                __instance.glowColor = new Color(0.2F, 0.9F, 1.0F, 0.25F);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "freeToPlay")
    public static class extendFreeToPlayMethod {
        public static boolean Postfix(boolean __result, AbstractCard __instance) {
            boolean res = AbstractDungeon.player != null &&
                    AbstractDungeon.currMapNode != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    AbstractDungeon.player.hasPower(makeID("FreeSkillPower"))
                    && __instance.type == AbstractCard.CardType.SKILL;

            return res || __result;
        }
    }

    @SpirePatch(clz = PoisonPower.class, method = "atStartOfTurn")
    public static class implementAccelerantCheckInPoisonPower {
        public static void Postfix(PoisonPower __instance, AbstractCreature ___source) {

            if (AbstractDungeon.player.hasPower(makeID("AccelerantPower"))) {
                int count = AbstractDungeon.player.getPower(makeID("AccelerantPower")).amount;
                for (int i = 0; i < count; i++) {
                    int newAmount = __instance.amount - (i + 1);
                    if (newAmount > 0)
                        AbstractDungeon.actionManager.addToBottom(new PoisonLoseHpAction(__instance.owner, ___source, newAmount, AbstractGameAction.AttackEffect.POISON));
                }
            }
        }
    }


    /*@SpirePatch(clz = CardCrawlGame.class, method = SpirePatch.CONSTRUCTOR)
    public static class WeakPowerAtDamageGivePatch {
        @SpireRawPatch
        public static void addAtDamageGive(CtBehavior ctBehavior) throws NotFoundException, CannotCompileException {
            CtClass weakPower = ctBehavior.getDeclaringClass().getClassPool().get(WeakPower.class.getName());
            String methodSource = "text of the method";
            CtMethod atDamageGive = CtNewMethod.make(methodSource, weakPower);
            weakPower.addMethod(atDamageGive);
        }
    }*/

    @SpirePatch(clz = AbstractPower.class, method = "atDamageReceive", paramtypez = {float.class, DamageInfo.DamageType.class})
    public static class extendWeakPowerToWorkWithTrackingMethod {

        @SpirePostfixPatch
        public static float Postfix(AbstractPower __instance, float __damage, DamageInfo.DamageType __type) {

            if (!Objects.equals(__instance.ID, WeakPower.POWER_ID)) {
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

    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnCards")
    public static class TriggerStartOfTurnForExhaustPilePatch {
        //Experimental, should not fuck with anything, but fix an issue with sly getting added to a card,
        // and it is then exhausted and then brought back during another turn with Exhume (SUPER NICHE)
        public static void Postfix(AbstractPlayer __instance) {

            for (AbstractCard c : __instance.exhaustPile.group) {
                if (c != null) {
                    c.atTurnStart();
                }
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "atTurnStart")
    public static class resetSlyForTurnAtStartOfTurnPatch {
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
        public static void Prefix(HandCardSelectScreen __instance, CardGroup ___hand, boolean ___forTransform, boolean ___forUpgrade) {
            if (___forTransform || ___forUpgrade) {
                return;
            }


            /*CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
                @Override
                public boolean test(AbstractCard card) {
                    //CardBorderGlowManager.
                    return (SlyField.slyForTurn.get(card) || SlyField.sly.get(card));
                }

                @Override
                public Color getColor(AbstractCard card) {
                    return Color.GOLD.cpy();
                }

                @Override
                public String glowID() {
                    return makeID("glowDuringDiscard");
                    //return a string to be used as a unique ID for this glow.
                    //It's recommended to follow the usual modding convention of "modname:name"
                }
            });*/
        }

        public static void Postfix(HandCardSelectScreen __instance, CardGroup ___hand, boolean ___forTransform, boolean ___forUpgrade) {

            if (___forTransform || ___forUpgrade) {
                return;
            }


            /*CardBorderGlowManager.removeGlowInfo(makeID("glowDuringDiscard"));*/

            //if()
            for (AbstractCard c : ___hand.group) {
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
                System.out.println("Sly!");
                AbstractMonster m = AbstractDungeon.getRandomMonster();
                if (!m.isDead && !m.escaped) {
                    if (!AbstractDungeon.player.endTurnQueued) {
                        __instance.use(AbstractDungeon.player, m);

                        AbstractDungeon.actionManager.addToBottom(new SlyAction(__instance, m));
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


    @SpirePatch(
            clz = AbstractCreature.class,
            method = "renderRedHealthBar"
    )
    public static class PostRenderHook {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"sb", "poisonAmt"}
        )
        public static void Insert(AbstractCreature __instance, SpriteBatch sb, @ByRef int[] poisonAmt) {
            if (AbstractDungeon.player.hasPower(makeID("AccelerantPower"))) {
                poisonAmt[0] = poisonAmt[0] * Math.min((AbstractDungeon.player.getPower(makeID("AccelerantPower")).amount + 1), __instance.getPower("Poison").amount);
                poisonAmt[0] -= Math.min((AbstractDungeon.player.getPower(makeID("AccelerantPower")).amount), __instance.getPower("Poison").amount);
            }
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {

                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "getPower");

                int[] temp = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
                temp[0] = temp[0] + 1;
                return temp;
            }
        }
    }

    public static int totalCardsDrawnThisCombat = 0;

    /*@SpirePatch(clz = GameActionManager.class, method = SpirePatch.CONSTRUCTOR)
    public static class AddingExtraTrackingForMurder {
        int totalCardsDrawnThisCombat = 0;
        public static void Postfix(GameActionManager __instance)
        {
            //ReflectionHacks.setPrivateStatic(GameActionManager.class, "totalCardsDrawnThisCombat", 0);
        }
    }*/

    @SpirePatch(clz = DrawCardAction.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCreature.class, int.class, boolean.class})
    public static class TrackingDrawInDrawCardAction {
        public static void Postfix(DrawCardAction __instance) {
            //int temp = (int)ReflectionHacks.getPrivateStatic(GameActionManager.class, "totalCardsDrawnThisCombat") + __instance.amount;
            Patches.totalCardsDrawnThisCombat += Math.min(__instance.amount, AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size());
            //ReflectionHacks.setPrivateStatic(GameActionManager.class, "totalCardsDrawnThisCombat", temp);
        }
    }

    /*@SpirePatch(
            clz = CardCrawlGame.class,
            method = "loadPostCombat"
    )
    public static class FixCardRewardsNotGettingSaved {
        public static boolean skipFirst = true;

        public static void Postfix(CardCrawlGame __instance, SaveFile saveFile) {
            if (!saveFile.smoked) {


                for (RewardSave i : saveFile.combat_rewards) {
                    switch (i.type) {
                        case "CARD":
                            if (skipFirst) {
                                skipFirst = false;
                                break;
                            }
                            AbstractDungeon.getCurrRoom().addCardToRewards();
                            break;
                        case "GOLD":
                        case "RELIC":
                        case "POTION":
                        case "STOLEN_GOLD":
                        case "SAPPHIRE_KEY":
                        case "EMERALD_KEY":
                        default:
                    }
                }
                skipFirst = true;
            }
        }
    }
*/

    /*
    * public DrawCardAction(AbstractCreature source, int amount, boolean endTurnDraw) {
        this.shuffleCheck = false;
        this.clearDrawHistory = true;
        this.followUpAction = null;
        if (endTurnDraw) {
            AbstractDungeon.topLevelEffects.add(new PlayerTurnEffect());
        }

        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.DRAW;
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FASTER;
        }

    }*/

    @SpirePatch(clz = AbstractDungeon.class, method = "nextRoomTransitionStart")
    public static class cleatHuntLoadedValueOnRoomTransition {
        public static void Postfix(/*AbstractDungeon __instance*/) {

            HuntCardReward.numberOfCardRewardsToRegenerate = 0;

        }
    }
    /* RESET THE HUNT SAVE STUFF HERE:
    * public static void nextRoomTransitionStart() {
        fadeOut();
        waitingOnFadeOut = true;
        overlayMenu.proceedButton.hide();
        if (ModHelper.isModEnabled("Terminal")) {
            player.decreaseMaxHealth(1);
        }

    }*/

    @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
    public static class addHuntCardToRewardOnLoad {
        public static void Postfix(CombatRewardScreen __instance) {

            if ((AbstractDungeon.getCurrRoom().event == null || AbstractDungeon.getCurrRoom().event != null && !AbstractDungeon.getCurrRoom().event.noCardsInRewards) && !(AbstractDungeon.getCurrRoom() instanceof TreasureRoom) && !(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {


                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom || (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) || (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
                    for (int i = 0; i < HuntCardReward.numberOfCardRewardsToRegenerate; i++) {
                        RewardItem cardReward = new RewardItem();
                        if (!cardReward.cards.isEmpty()) {
                            __instance.rewards.add(cardReward);
                        }
                    }
                }

            }

            //AbstractDungeon.overlayMenu.proceedButton.show();
            __instance.hasTakenAll = false;
            __instance.positionRewards();

        }
    }

    /*
    * public void setupItemReward() {
        this.rewardAnimTimer = 0.2F;
        InputHelper.justClickedLeft = false;
        this.rewards = new ArrayList(AbstractDungeon.getCurrRoom().rewards);
        if ((AbstractDungeon.getCurrRoom().event == null || AbstractDungeon.getCurrRoom().event != null && !AbstractDungeon.getCurrRoom().event.noCardsInRewards) && !(AbstractDungeon.getCurrRoom() instanceof TreasureRoom) && !(AbstractDungeon.getCurrRoom() instanceof RestRoom)) {
            if (ModHelper.isModEnabled("Vintage") && AbstractDungeon.getCurrRoom() instanceof MonsterRoom) {
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite || AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                    RewardItem cardReward = new RewardItem();
                    if (cardReward.cards.size() > 0) {
                        this.rewards.add(cardReward);
                    }
                }
            } else {
                RewardItem cardReward = new RewardItem();
                if (cardReward.cards.size() > 0) {
                    this.rewards.add(cardReward);
                }

                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom && AbstractDungeon.player.hasRelic("Prayer Wheel") && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) && !(AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
                    cardReward = new RewardItem();
                    if (cardReward.cards.size() > 0) {
                        this.rewards.add(cardReward);
                    }
                }
            }
        }

        AbstractDungeon.overlayMenu.proceedButton.show();
        this.hasTakenAll = false;
        this.positionRewards();
    }*/
}