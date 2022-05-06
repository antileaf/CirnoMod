package ThMod.cards.CirnoChoiceCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class YellowTextbook extends MathTextbook {
	
	public static final String ID = "YellowTextbook";
	public static final String IMG_PATH = "img/cards/YellowTextbook.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	
	public YellowTextbook() {
		super(
				ID,
				NAME,
				IMG_PATH,
				DESCRIPTION
		);
		
		this.cardType = CardType.SKILL;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new YellowTextbook();
	}
}