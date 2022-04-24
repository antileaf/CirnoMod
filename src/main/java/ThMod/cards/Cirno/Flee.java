package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FleeingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flee extends AbstractCirnoCard {
	
	public static final String ID = "Flee";
	public static final String IMG_PATH = "img/cards/Flee.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ROUND_CNT = 4;
	private static final int MOTIVATION_COST = -1;
	
	public Flee() {
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
		
		this.magicNumber = this.baseMagicNumber = ROUND_CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.setMotivated(ThMod.isMotivated(this));
		
		int cnt = this.magicNumber;
		if (this.isMotivated)
			cnt -= p.getPower("Motivation").amount;
		
		this.addToBot(new ApplyPowerAction(p, p, new FleeingPower(cnt)));
	}
	
	public AbstractCard makeCopy() {
		return new Flee();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			this.isInnate = true;
			initializeDescription();
		}
	}
}