package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ColdBeer extends AbstractCirnoCard {
	
	public static final String ID = ColdBeer.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int MOTIVATION_GAIN = 5;
	private static final int UPGRADE_PLUS_MOTIVATION_GAIN = 2;
	private static final int STRENGTH_LOSE = 99;
	
	public ColdBeer() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);
		
		this.magicNumber = this.baseMagicNumber = MOTIVATION_GAIN;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.magicNumber)));
		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.magicNumber)));
		
		this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -STRENGTH_LOSE)));
		if (!p.hasPower("Artifact"))
			this.addToBot(new ApplyPowerAction(p, p,
					new GainStrengthPower(p, STRENGTH_LOSE)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ColdBeer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_MOTIVATION_GAIN);
			this.initializeDescription();
		}
	}
}