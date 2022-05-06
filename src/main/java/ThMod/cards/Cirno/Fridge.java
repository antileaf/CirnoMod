package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FridgePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Fridge extends AbstractCirnoCard {
	
	public static final String ID = "Fridge";
	public static final String IMG_PATH = "img/cards/Fridge.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	
	public Fridge() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.SELF
		);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new FridgePower()));
	}
	
	public AbstractCard makeCopy() {
		return new Fridge();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			this.isInnate = true;
			initializeDescription();
		}
	}
}