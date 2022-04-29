package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.PerfectGlacialistAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PerfectGlacialist extends AbstractCirnoCard {
	
	public static final String ID = "PerfectGlacialist";
	public static final String IMG_PATH = "img/cards/PerfectGlacialist.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_ATTACK_DMG = 3;
	
	public PerfectGlacialist() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.calculateCardDamage(m);
		this.addToBot(new PerfectGlacialistAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				this.damage));
	}
	
	public AbstractCard makeCopy() {
		return new PerfectGlacialist();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
			initializeDescription();
		}
	}
}