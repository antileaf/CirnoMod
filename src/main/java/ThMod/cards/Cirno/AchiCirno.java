package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class AchiCirno extends AbstractCirnoCard {
	
	public static final String ID = AchiCirno.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	private static final int DRAW_CNT = 9;
	private static final int SELF_DAMAGE = 9;
	private static final int CNT = 2;
	private static final int MOTIVATED_CNT = 1;
	private static final int MOTIVATION_COST = 1;
	
	public AchiCirno() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.NONE
		);
		
		this.magicNumber = this.baseMagicNumber = SELF_DAMAGE;
		this.motivationCost = MOTIVATION_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		this.addToBot(new DrawCardAction(DRAW_CNT));
		
		int cnt = (this.isMotivated ? MOTIVATED_CNT : CNT);
		for (int i = 0; i < cnt; i++)
			this.addToBot(new DamageAction(p, new DamageInfo(p,
					this.magicNumber, DamageInfo.DamageType.THORNS)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new AchiCirno();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeBaseCost(UPGRADED_COST);
			initializeDescription();
		}
	}
}