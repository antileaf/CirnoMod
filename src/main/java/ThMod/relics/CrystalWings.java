package ThMod.relics;

import ThMod.powers.Cirno.ChillPower;
import ThMod.powers.Cirno.MotivationPower;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CrystalWings extends CustomRelic {
	
	public static final String ID = "CrystalWings";
	private static final String IMG = "img/relics/CrystalWings.png";
	private static final String IMG_OTL = "img/relics/outline/CrystalWings.png";
	
	public CrystalWings() {
		super(
				ID,
				ImageMaster.loadImage(IMG),
				ImageMaster.loadImage(IMG_OTL),
				RelicTier.STARTER,
				LandingSound.MAGICAL
		);
	}
	
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0];
	}
	
	public AbstractRelic makeCopy() {
		return new CrystalWings();
	}
	
	@Override
	public void atBattleStart() {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
				AbstractDungeon.player, AbstractDungeon.player, new ChillPower(2)));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
				AbstractDungeon.player, AbstractDungeon.player, new MotivationPower(1)));
	}
}