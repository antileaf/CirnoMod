package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FridgePower extends AbstractPower {
	
	public static final String POWER_ID = "FridgePower";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public boolean used;
	
	public FridgePower() {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = -1;
		this.used = false;
		
		this.type = PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FridgePower.png");
	}
	
	@Override
	public void updateDescription() {
		if (!this.used)
			this.description = DESCRIPTIONS[0];
		else
			this.description = DESCRIPTIONS[1];
	}
	
	@Override
	public void onDeath() {
		if (!this.used) {
			int healAmt = (int)(AbstractDungeon.player.maxHealth * 0.3);
			if (healAmt < 1)
				healAmt = 1;
			
			AbstractDungeon.player.heal(healAmt, true);
			
			this.used = true;
			updateDescription();
		}
	}
}