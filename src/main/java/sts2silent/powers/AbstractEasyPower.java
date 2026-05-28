package sts2silent.powers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sts2silent.ModFile;
import sts2silent.util.TexLoader;

public abstract class AbstractEasyPower extends AbstractPower {
    public int amount2 = -1;
    public boolean isTwoAmount = false;
    public static Color redColor2 = Color.RED.cpy();
    public static Color greenColor2 = Color.GREEN.cpy();
    public boolean canGoNegative2 = false;
    protected float fontScale2 = 1.0F;

    private PowerStrings powerStrings = null;


    public AbstractEasyPower(String ID, String NAME, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        this.ID = ID;
        this.isTurnBased = isTurnBased;

        this.name = NAME;

        this.owner = owner;
        this.amount = amount;
        this.type = powerType;

        powerStrings = CardCrawlGame.languagePack.getPowerStrings(NAME);
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        Texture normalTexture = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/" + ID.replaceAll(ModFile.modID + ":", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }
    @Override
    public void update(int slot){
        super.update(slot);
        updateSecondFontScale();
    }
    private void updateSecondFontScale() {
        //super.updateFontScale();
        if (this.fontScale2 != 1.0F) {
            this.fontScale2 = MathUtils.lerp(this.fontScale2, 1.0F, Gdx.graphics.getDeltaTime() * 10.0F);
            if (this.fontScale2 - 1.0F < 0.05F) {
                this.fontScale2 = 1.0F;
            }
        }

    }

    public void reducePowerOfTwo(int reduceAmount) {
        if (this.amount2 - reduceAmount <= 0) {
            this.fontScale2 = 8.0F;
            this.amount2 = 0;
        } else {
            this.fontScale2 = 8.0F;
            this.amount2 -= reduceAmount;
        }

    }

    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (!isTwoAmount)
            return;
        if (amount2 > 0) {
            if (!isTurnBased) {
                greenColor2.a = c.a;
                c = greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale2, c);
        } else if (amount2 < 0 && canGoNegative2) {
            redColor2.a = c.a;
            c = redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(amount2), x, y + 15.0F * Settings.scale, fontScale2, c);
        }
    }
}