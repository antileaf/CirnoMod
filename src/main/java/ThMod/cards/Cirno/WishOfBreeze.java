package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.WishOfBreezePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WishOfBreeze extends AbstractCirnoCard {
	
	public static final String ID = "WishOfBreeze";
	public static final String IMG_PATH = "img/cards/WishOfBreeze.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int CNT = 2;
	private static final int UPGRADE_PLUS_CNT = 1;
	
	public WishOfBreeze() {
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
		
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new WishOfBreezePower(this.magicNumber)));
	}
	
	public AbstractCard makeCopy() {
		return new WishOfBreeze();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeMagicNumber(UPGRADE_PLUS_CNT);
			initializeDescription();
		}
	}
}