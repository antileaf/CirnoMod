package ThMod.powers.Cirno;

import ThMod.action.CirnoAnonymousAction;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MinusKPower extends AbstractPower {
	
	public static final String POWER_ID = MinusKPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	boolean available;
	
	public MinusKPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/MinusKPower.png");
		
		this.available = true;
		this.updateDescription();
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + " NL " +
				(this.available ?
				DESCRIPTIONS[2] :
				DESCRIPTIONS[3]);
	}
	
	@Override
	public void atStartOfTurn() {
		this.available = true;
		this.updateDescription();
	}
	
	@Override
	public void onExhaust(AbstractCard card) {
		if (this.available) {
			if (this.amount > 0)
				this.addToBot(new GainEnergyAction(2));
			
			if (--this.amount <= 0)
				this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
			
			this.available = false;
			
			this.addToBot(new CirnoAnonymousAction(this::updateDescription));
//			this.updateDescription();
		}
	}
}