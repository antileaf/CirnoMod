package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FairySpin extends AbstractCirnoCard {
	
	public static final String ID = "FairySpin";
	public static final String IMG_PATH = "img/cards/FairySpin.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	
	private static final int ATTACK_DMG = 11;
	private static final int CNT = 1;
	
	private static final int UPGRADE_PLUS_DMG = 3;
	
	private static final int MOTIVATION_COST = -1;
	
	public FairySpin() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.ALL_ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = CNT;
		this.motivationCost = MOTIVATION_COST;
//		this.damageType = DamageInfo.DamageType.NORMAL;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.setMotivated(ThMod.isMotivated(this));
		
		int cnt = this.magicNumber;
		if (this.isMotivated)
			cnt += p.getPower("Motivation").amount;
		
		for (int i = 0; i < cnt; i++) {
			this.addToTop(new DamageRandomEnemyAction(
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
		
		this.addToTop(new MakeTempCardInDrawPileAction(new Dazed(),
				cnt, true, true)); // 一起洗
	}
	
	public AbstractCard makeCopy() {
		return new FairySpin();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}