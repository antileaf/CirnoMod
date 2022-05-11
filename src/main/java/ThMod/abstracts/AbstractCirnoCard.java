package ThMod.abstracts;

import ThMod.ThMod;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static ThMod.ThMod.CHILLED_FLAVOR;

public abstract class AbstractCirnoCard extends CustomCard implements SpawnModificationCard {
	protected static final Color CYAN_COLOR = new Color(0f, 204f / 255f, 0f, 1f);
	
	public int motivationCost = 0; // -1 means all
	public int motivationGain = 0;
	public int chillGain = 0;
	public boolean isMotivated = false;
	public int motivatedCnt = 0;
	
	public boolean dontUsePatch = false;
	
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
		
//		FlavorText.AbstractCardFlavorFields.textColor.set(this, CHILLED);
		FlavorText.AbstractCardFlavorFields.boxColor.set(this, CHILLED_FLAVOR);
		
//		this.Abstract = new SpireField(var10002::cpy);
//		var10002 = Color.BLACK;
//		var10002.getClass();
//		textColor = new SpireField(var10002::cpy);
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
