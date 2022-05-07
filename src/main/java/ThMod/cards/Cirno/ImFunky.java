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
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 3;
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
		
		this.magicNumber = this.baseMagicNumber = FUNKY_GAIN;
		this.isEthereal = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new FunkyPower(this.magicNumber)));
	}
	
	public AbstractCard makeCopy() {
		return new ImFunky();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
//			upgradeBaseCost(UPGRADED_COST);
			this.isEthereal = false;
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}