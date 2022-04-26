package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import ThMod.powers.Cirno.MotivationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Chirumiru extends AbstractCirnoCard {
	
	public static final String ID = "Chirumiru";
	public static final String IMG_PATH = "img/cards/Chirumiru.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int CHILL = 2;
	private static final int UPGRADE_PLUS_CHILL = 1;
	private static final int MOTIVATION_GAIN = 1;
	private static final int UPGRADE_PLUS_MOTIVATION_GAIN = 1;
	private static final int DRAW_CNT = 1;
	
	public Chirumiru() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			AbstractCard.CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			AbstractCard.CardRarity.UNCOMMON,
			AbstractCard.CardTarget.SELF
		);
		
		this.chillGain = this.damage = this.baseDamage = CHILL;
		this.motivationGain = this.block = this.baseBlock = MOTIVATION_GAIN;
		this.magicNumber = this.baseMagicNumber = DRAW_CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.chillGain)));
		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.motivationGain)));
		this.addToBot(new DrawCardAction(this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new Chirumiru();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeDamage(UPGRADE_PLUS_CHILL);
			upgradeBlock(UPGRADE_PLUS_MOTIVATION_GAIN);
			this.chillGain = this.damage;
			this.motivationGain = this.block;
			initializeDescription();
		}
	}
}