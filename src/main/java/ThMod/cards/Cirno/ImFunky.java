package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FunkyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ImFunky extends AbstractCirnoCard {
	
	public static final String ID = "ImFunky";
	public static final String IMG_PATH = "img/cards/ImFunky.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 3;
	private static final int UPGRADED_COST = 2;
	private static final int FUNKY_GAIN = 1;
	
	public ImFunky() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.SELF
		);
		
		this.magicNumber = FUNKY_GAIN;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToTop(new ApplyPowerAction(p, p, new FunkyPower(this.magicNumber)));
	}
	
	public AbstractCard makeCopy() {
		return new ImFunky();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeBaseCost(UPGRADED_COST);
			initializeDescription();
		}
	}
}