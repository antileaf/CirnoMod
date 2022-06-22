package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrostColumns extends AbstractCirnoCard {
	
	public static final String ID = FrostColumns.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int CHILL = 4;
	
	public FrostColumns() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.SELF
		);
		
		this.magicNumber = this.baseMagicNumber = CHILL;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.magicNumber)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FrostColumns();
	}
	
	@Override
	public boolean canUpgrade() {
		return true;
	}
	
	@Override
	public void upgradeName() {
		this.upgraded = true;
		this.name = NAME + "+" + ++this.timesUpgraded;
	}
	
	public void upgrade() {
		this.upgradeName();
		
		this.upgradeMagicNumber(this.timesUpgraded / 2);
		this.initializeDescription();
	}
}