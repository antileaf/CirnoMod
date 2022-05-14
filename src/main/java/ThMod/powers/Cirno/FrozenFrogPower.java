package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FrozenFrogPower extends AbstractPower {
	
	public static final String POWER_ID = FrozenFrogPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	private static final int MULTIPLIER = 2;
	
	public FrozenFrogPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + MULTIPLIER * this.amount +
				DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type == AbstractCard.CardType.POWER) {
			this.flash();
			this.addToTop(new ApplyPowerAction(this.owner, this.owner,
					new MotivationPower(this.amount)));
			this.addToTop(new ApplyPowerAction(this.owner, this.owner,
					new ChillPower(MULTIPLIER * this.amount)));
		}
	}
}