package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.jetbrains.annotations.NotNull;

public class FrostKingPower extends AbstractPower {
	
	public static final String POWER_ID = FrostKingPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
//	public HashSet<String> visited;
	
	public FrostKingPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
//		this.visited = new HashSet<>();
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FrostKingBlockPower.png");
	}
	
	public void trigger(@NotNull AbstractCard card) {
		this.addToBot(new GainBlockAction(this.owner, this.amount));
		
//		this.visited.add(card.cardID);
		this.updateDescription();
	}
	
	@Override
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] +
			this.amount; // * 2 + DESCRIPTIONS[2] + " NL " +
//				DESCRIPTIONS[3] + this.visited.size() + DESCRIPTIONS[4];
	}
	
//	@Override
//	public void onSpecificTrigger() {
//		this.flash();
//		this.addToBot(new GainBlockAction(this.owner, this.amount));
//	}
}