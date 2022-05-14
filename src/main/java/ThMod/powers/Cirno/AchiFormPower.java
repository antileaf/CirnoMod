package ThMod.powers.Cirno;

import ThMod.action.AchiFormMainAction;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AchiFormPower extends AbstractPower {
	
	public static final String POWER_ID = AchiFormPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public AchiFormPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FunkyPower.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}
	
	@Override
	public void atStartOfTurnPostDraw() {
		AbstractPlayer p = AbstractDungeon.player;
		
		if (p.drawPile.size() + p.discardPile.size() == 0)
			return;
		
		this.addToTop(new AchiFormMainAction(this.amount, DESCRIPTIONS[2] + " (" + this.name + ")"));
		
		if (p.drawPile.size() < this.amount)
			this.addToTop(new EmptyDeckShuffleAction());
	}
}