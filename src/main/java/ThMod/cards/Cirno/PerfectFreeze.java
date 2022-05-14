package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

public class PerfectFreeze extends AbstractCirnoCard {
	
	public static final String ID = PerfectFreeze.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
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
		this.magicNumber = this.baseMagicNumber = this.motivationCost = MOTIVATION_COST;
		this.isMultiDamage = true;
	}
	
//	@Override
//	public void initializeDescription() {
//
//
//		super.initializeDescription();
//	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
		
		this.rawDescription = (this.upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION);
		
		if (AbstractDungeon.isPlayerInDungeon()) {
			int cnt = (this.upgraded ? UPGRADE_PLUS_CNT : CNT);
			if (AbstractDungeon.player.hasPower(ChillPower.POWER_ID))
				cnt += AbstractDungeon.player.getPower(ChillPower.POWER_ID).amount;
			
			this.rawDescription += " NL " + cardStrings.EXTENDED_DESCRIPTION[0] +
					cnt + cardStrings.EXTENDED_DESCRIPTION[1];
		}
		
		this.initializeDescription();
	}
	
	@Override
	public void onMoveToDiscard() {
		this.rawDescription = (this.upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION);
		this.initializeDescription();
	}
	
	@Override
	public void triggerOnGlowCheck() {
		if (ThMod.calcMotivated(this) > 0)
			this.glowColor = CYAN_COLOR.cpy();
		else
			this.glowColor = Color.DARK_GRAY.cpy();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		if (this.isMotivated) {
			this.calculateCardDamage(null);
			
			int cnt = (this.upgraded ? UPGRADE_PLUS_CNT : CNT);
			if (p.hasPower(ChillPower.POWER_ID))
				cnt += p.getPower(ChillPower.POWER_ID).amount;
			
			if (cnt > 0) {
				this.addToBot(new VFXAction(new BlizzardEffect(cnt - 5, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
				
				for (int i = 0; i < cnt; i++) {
					this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage,
							this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
			}
			
//			this.addToTop(new ReducePowerAction(p, p, "MotivationPower", this.motivationCost));
		}
		else {
			AbstractDungeon.effectList.add(new ThoughtBubble(
					AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F,
					cardStrings.EXTENDED_DESCRIPTION[2], true));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new PerfectFreeze();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.upgradeMagicNumber(UPGRADED_MOTIVATION_COST - MOTIVATION_COST);
			this.motivationCost = UPGRADED_MOTIVATION_COST;
			
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}