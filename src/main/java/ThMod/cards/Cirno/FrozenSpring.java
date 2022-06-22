package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FrozenSpringHealPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrozenSpring extends AbstractCirnoCard {
	
	public static final String ID = FrozenSpring.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	private static final int CNT = 9;
	
	public FrozenSpring() {
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
		
		this.magicNumber = this.baseMagicNumber = 1;
		this.tags.add(CardTags.HEALING);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new ApplyPowerAction(p, p, new FrozenSpringHealPower(CNT)));
		
//		if (this.upgraded)
//			this.addToBot(new ApplyPowerAction(p, p, new FrozenSpringMotivationPower(CNT)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FrozenSpring();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
//			this.rawDescription = UPGRADE_DESCRIPTION;
			this.upgradeBaseCost(UPGRADED_COST);
			this.initializeDescription();
		}
	}
}