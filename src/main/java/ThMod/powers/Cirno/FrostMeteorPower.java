package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FrostMeteorPower extends AbstractPower {
	
	public static final String POWER_ID = FrostMeteorPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public FrostMeteorPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FrostMeteorPower.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.purgeOnUse || !action.exhaustCard ||
				(card.type != AbstractCard.CardType.ATTACK && card.type != AbstractCard.CardType.SKILL))
			return;
		
		this.flash();
		action.exhaustCard = false;
		
		if (--this.amount <= 0)
			this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
}