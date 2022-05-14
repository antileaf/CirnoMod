package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.ColdWindApplyAction;
import ThMod.cards.CirnoDerivation.IceConical;
import ThMod.cards.CirnoDerivation.IceCube;
import ThMod.patches.AbstractCardEnum;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ColdWind extends AbstractCirnoCard {
	
	public static final String ID = ColdWind.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	
	public ColdWind() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);
		
		MultiCardPreview.add(this, new IceConical(), new IceCube());
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ColdWindApplyAction(this.upgraded));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ColdWind();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			for (AbstractCard c : MultiCardPreview.multiCardPreview.get(this))
				c.upgrade();
			
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}