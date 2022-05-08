package ThMod.cards.CirnoDerivation;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class StarSapphiresHelp extends SanyouseisHelp {
	
	public static final String ID = StarSapphiresHelp.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	
	public StarSapphiresHelp() {
		super(
			ID,
			NAME,
			IMG_PATH,
			DESCRIPTION
		);
		
		this.cardType = CardType.POWER;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new StarSapphiresHelp();
	}
}