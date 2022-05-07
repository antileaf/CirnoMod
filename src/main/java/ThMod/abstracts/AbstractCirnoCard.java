package ThMod.abstracts;

import ThMod.ThMod;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractCirnoCard extends CustomCard {
	protected static final Color CYAN_COLOR = new Color(0f, 204f / 255f, 0f, 1f);
	
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
		this.isMotivated = (motivatedCnt > 0);
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
	
	@Override
	public void triggerOnGlowCheck() {
		this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
		
		if (ThMod.calcMotivated(this) > 0)
			this.glowColor = CYAN_COLOR.cpy();
	}
}
