package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FreezeTouchMePower extends AbstractPower {
	
	public static final String POWER_ID = "FreezeTouchMePower";
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
		updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FreezeTouchMePower.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (info.type != DamageInfo.DamageType.HP_LOSS) {
			int val = Integer.min(damageAmount, this.owner.currentBlock) / 2;
			if (val > 0)
				this.addToTop(new GainBlockAction(this.owner, val));
			
			this.flash();
		}
		
		return damageAmount;
	}
	
	@Override
	public void atStartOfTurn() {
		if (--this.amount <= 0)
			this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
}