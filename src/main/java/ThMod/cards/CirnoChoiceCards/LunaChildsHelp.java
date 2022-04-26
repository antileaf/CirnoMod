package ThMod.cards.CirnoChoiceCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public class LunaChildsHelp extends SanyouseisHelp {
	
	public static final String ID = "LunaChildsHelp";
	public static final String IMG_PATH = "img/cards/LunaChildsHelp.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	
	public LunaChildsHelp() {
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
		return new LunaChildsHelp();
	}
}