package ThMod.abstracts;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractCirnoCard extends CustomCard {
	
	public int motivationCost = 0; // -1 means all
	public int motivationGain = 0;
	public int chillGain = 0;
	public boolean isMotivated = false;
	public int motivatedCnt = 0;
	
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
				"img/cards/Cirno/Th123Cirno.png",
				cost,
				rawDescription,
				type,
				color,
				rarity,
				target
		);
	}
	
	public void setMotivated(int motivatedCnt) {
		this.isMotivated = motivatedCnt > 0;
		this.motivatedCnt = motivatedCnt;
	}
	
	@Override
	public AbstractCard makeStatEquivalentCopy() {
		AbstractCirnoCard card = (AbstractCirnoCard) super.makeStatEquivalentCopy();
		
		card.motivationCost = this.motivationCost;
		card.motivationGain = this.motivationGain;
		card.chillGain = this.chillGain;
		card.isMotivated = this.isMotivated;
		card.motivatedCnt = this.motivatedCnt;
		
		return card;
	}
}
