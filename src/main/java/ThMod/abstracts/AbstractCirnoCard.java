package ThMod.abstracts;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractCirnoCard extends CustomCard {
	
	public int motivationCost = 0; // -1 means all
	public int motivationGain = 0;
	public int chillGain = 0;
	public boolean isMotivated = false;
	
	public AbstractCirnoCard(
			String id,
			String name,
			String img,
			int cost,
			String rawDescription,
			AbstractCard.CardType type,
			AbstractCard.CardColor color,
			AbstractCard.CardRarity rarity,
			AbstractCard.CardTarget target
	) {
		super(
				id,
				name,
				img,
				cost,
				rawDescription,
				type,
				color,
				rarity,
				target
		);
	}
	
	public void setMotivated(boolean isMotivated) {
		this.isMotivated = isMotivated;
	}
}
