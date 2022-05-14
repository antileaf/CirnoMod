package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FreezeTouchMePower extends AbstractPower {
	
	public static final String POWER_ID = FreezeTouchMePower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public FreezeTouchMePower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FreezeTouchMePower.png");
	}
	
	public void triggerOnce(int amount) {
		if (amount > 0) {
			this.addToTop(new GainBlockAction(this.owner, amount));
			this.flash();
		}
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	// 相关功能在patch里
	
//	@Override // 据说 stslib 相关部分有 bug，没敢用
//	public int onLoseBlock(DamageInfo damageInfo, int i) {
//		return 0;
//	}
	
	@Override
	public void atStartOfTurn() {
		if (--this.amount <= 0)
			this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
}