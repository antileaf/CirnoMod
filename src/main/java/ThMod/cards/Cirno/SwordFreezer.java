package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class SwordFreezer extends AbstractCirnoCard {
	
	public static final String ID = "SwordFreezer";
	public static final String IMG_PATH = "img/cards/SwordFreezer.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MULTIPLIER = 2;
	private static final int UPGRADE_PLUS_MULTIPLIER = 1;
	private static final int MOTIVATION_COST = 1;
	private static final int BLOCK = 1;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	
	public SwordFreezer() {
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
		
		this.magicNumber = this.baseMagicNumber = MULTIPLIER;
		this.block = this.baseBlock = BLOCK;
		this.motivationCost = MOTIVATION_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.setMotivated(ThMod.calcMotivated(this));
		
		int attack = 0, skill = 0;
		for (AbstractCard card : p.hand.group) {
			if (card.type == CardType.ATTACK)
				attack++;
			if (card.type == CardType.SKILL)
				skill++;
		}
		
		this.addToBot(new ApplyPowerAction(p, p,
				new VigorPower(p, this.magicNumber * attack)));
		
		if (this.isMotivated)
			this.addToBot(new GainBlockAction(p, this.block * skill));
	}
	
	public AbstractCard makeCopy() {
		return new SwordFreezer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeMagicNumber(UPGRADE_PLUS_MULTIPLIER);
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			initializeDescription();
		}
	}
}