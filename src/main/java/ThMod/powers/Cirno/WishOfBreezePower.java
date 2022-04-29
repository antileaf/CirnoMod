package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class WishOfBreezePower extends AbstractPower {
	
	public static final String POWER_ID = "WishOfBreeze";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public WishOfBreezePower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/WishOfBreeze.png");
	}
	
	@Override
	public void updateDescription() { // TODO: 还没太懂这里的逻辑，后面看一下
//		if (this.cnt > 0) {
//			this.description =
//					(
//							DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]
//									+ "," + DESCRIPTIONS[2] + (int) Math.pow(2, this.cnt) + DESCRIPTIONS[3]
//					);
//		} else {
//			this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + ".");
//		}
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
	}
	
	@Override
	public void atEndOfRound() {
		AbstractPlayer p = AbstractDungeon.player;
		
		if (p.discardPile.isEmpty())
			return;
		
		int cnt = Integer.min(this.amount, p.discardPile.size());
		
		ArrayList<Integer> array = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			int x = AbstractDungeon.miscRng.random(0, p.discardPile.size() - 1);
			
			while (array.contains(x))
				x = AbstractDungeon.miscRng.random(0, p.discardPile.size() - 1);
			
			array.add(x);
		}
		
		ArrayList<AbstractCard> cards = new ArrayList<>();
		for (int i : array)
			cards.add(p.discardPile.group.get(i));
		
		for (AbstractCard card : cards) {
			p.discardPile.removeCard(card);
			p.hand.addToHand(card);
			card.lighten(false);
		}
		
		p.hand.refreshHandLayout();
	}
}