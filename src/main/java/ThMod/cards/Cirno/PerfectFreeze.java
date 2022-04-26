package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

public class PerfectFreeze extends AbstractCirnoCard {
	
	public static final String ID = "PerfectFreeze";
	public static final String IMG_PATH = "img/cards/PerfectFreeze.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	
	private static final int ATTACK_DMG = 0;
	
	private static final int UPGRADE_PLUS_DMG = 1;
	private static final int CNT = 0;
	private static final int UPGRADE_PLUS_CNT = 1;
	
	private static final int MOTIVATION_COST = 3;
	private static final int UPGRADED_MOTIVATION_COST = 2;
	
	public PerfectFreeze() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.ALL_ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = CNT;
		this.motivationCost = MOTIVATION_COST;
		this.isMultiDamage = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.setMotivated(ThMod.calcMotivated(this));
		
		if (this.isMotivated) {
			this.calculateCardDamage(null);
			
			int cnt = this.magicNumber;
			if (p.hasPower("ChillPower"))
				cnt += p.getPower("ChillPower").amount;
			
			if (cnt > 0) {
				this.addToBot(new VFXAction(new BlizzardEffect(cnt - 5, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
				
				for (int i = 0; i < cnt; i++) {
					this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage,
							this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
			}
			
			this.addToTop(new ReducePowerAction(p, p, "Motivation", this.motivationCost));
		}
	}
	
	public AbstractCard makeCopy() {
		return new PerfectFreeze();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.motivationCost = UPGRADED_MOTIVATION_COST;
			initializeDescription();
		}
	}
}