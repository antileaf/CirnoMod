package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.ButterflyFairysHelpSingleAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ButterflyFairysHelp extends AbstractCirnoCard {
	
	public static final String ID = ButterflyFairysHelp.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	
	private static final int ATTACK_DMG = 1;
	
	private static final int CNT = 3;
//	private static final int UPGRADE_PLUS_CNT = 2;
	
	public ButterflyFairysHelp() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ALL_ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = CNT;
		this.exhaust = true;
//		this.damageType = DamageInfo.DamageType.NORMAL;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for (int i = 0; i < this.magicNumber; i++) {
			this.addToBot(new ButterflyFairysHelpSingleAction(
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					this.upgraded));
			
//			this.addToBot(new DamageRandomEnemyAction(
//					new DamageInfo(p, this.damage, this.damageTypeForTurn),
//					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ButterflyFairysHelp();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
//			upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}