package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FleeingPower extends AbstractPower {
	
	public static final String POWER_ID = FleeingPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public FleeingPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/Fleeing.png");
		
		this.isTurnBased = true;
	}
	
	@Override
	public void stackPower(int stackAmount) {
		this.amount = Integer.min(this.amount, stackAmount);
	}
	
	@Override
	public void updateDescription() {
		this.description = this.amount + DESCRIPTIONS[0];
	}
	
	@Override
	public void atEndOfRound() {
		if (this.amount <= 1) {
			new SmokeBomb().use(this.owner);
			
			return;
		}
		
		this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
	}
}