package ThMod.powers.Cirno;

import ThMod.action.CirnoExhaustSpecificCardAction;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class LunaticFairyPower extends AbstractPower {
	
	public static final String POWER_ID = LunaticFairyPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public LunaticFairyPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/LunaticFairyPower.png");
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
	}
	
	@Override
	public void atStartOfTurn() {
		this.addToBot(new GainEnergyAction(this.amount));
	}
	
	@Override
	public void onCardDraw(AbstractCard card) {
		this.addToTop(new CirnoExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
		
		if (card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE) {
			for (int i = 0; i < this.amount; i++) {
				this.addToBot(new MakeTempCardInHandAction(
						AbstractDungeon.returnTrulyRandomCardInCombat(card.type).makeCopy()));
//				ThMod.frostKing();
			}
		}
	}
}