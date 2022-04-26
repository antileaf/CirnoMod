package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IcicleShot extends AbstractCirnoCard {
	
	public static final String ID = "IcicleShot";
	public static final String IMG_PATH = "img/cards/IcicleShot.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	
	private static final int ATTACK_DMG = 7;
	
	private static final int UPGRADE_PLUS_DMG = 2;
	
	private static final int CNT = 2;
	
	private static final int MOTIVATION_COST = 1;
	
	private static final int MOTIVATED_CNT = 3;
	
	public IcicleShot() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.BASIC,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = CNT;
		this.motivationCost = MOTIVATION_COST;
//		this.damageType = DamageInfo.DamageType.NORMAL;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.setMotivated(ThMod.calcMotivated(this));
		int cnt = (this.isMotivated ? CNT : MOTIVATED_CNT);
		
		for (int i = 0; i < cnt; i++) {
			this.addToBot(new DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL, false));
		}
		
		this.addToTop(new ReducePowerAction(p, p, "Motivation", this.motivationCost));
	}
	
	public AbstractCard makeCopy() {
		return new IcicleShot();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}