package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.MinusKPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MinusK extends AbstractCirnoCard {
	
	public static final String ID = "ColdBeer";
	public static final String IMG_PATH = "img/cards/ColdBeer.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = -1;
	
	public MinusK() {
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
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new MinusKPower(
				EnergyPanel.totalCount + (this.upgraded ? 1 : 0))));
		
		if (!this.freeToPlayOnce)
			p.energy.use(EnergyPanel.totalCount);
	}
	
	public AbstractCard makeCopy() {
		return new MinusK();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}