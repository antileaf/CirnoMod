package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShowOff extends AbstractCirnoCard {
	
	public static final String ID = ShowOff.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int CHILL_GAIN = 1;
	private static final int UPGRADE_PLUS_CHILL_GAIN = 1;
	private static final int MOTIVATION_GAIN = 1;
	private static final int UPGRADE_PLUS_MOTIVATION_GAIN = 1;
	
	public ShowOff() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.BASIC,
			CardTarget.SELF
		);
		
		this.chillGain = this.damage = this.baseDamage = CHILL_GAIN;
		this.motivationGain = this.block = this.baseBlock = MOTIVATION_GAIN;
		this.magicNumber = this.baseMagicNumber = CHILL_GAIN;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.chillGain)));
		this.addToBot(new WaitAction(0.1F));
		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.motivationGain)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ShowOff();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.chillGain += UPGRADE_PLUS_CHILL_GAIN;
			this.motivationGain += UPGRADE_PLUS_MOTIVATION_GAIN;
			this.upgradeMagicNumber(1);
//			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}