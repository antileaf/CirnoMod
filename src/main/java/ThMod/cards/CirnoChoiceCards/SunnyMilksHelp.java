package ThMod.cards.CirnoChoiceCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class SunnyMilksHelp extends SanyouseisHelp {
	
	public static final String ID = "SunnyMilksHelp";
	public static final String IMG_PATH = "img/cards/SunnyMilksHelp.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	
	public SunnyMilksHelp() {
		super(
			ID,
			NAME,
			IMG_PATH,
			DESCRIPTION
		);
		
		this.cardType = CardType.ATTACK;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SunnyMilksHelp();
	}
}