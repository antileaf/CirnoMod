package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SuperIceKick extends AbstractCirnoCard {
	
	public static final String ID = SuperIceKick.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	
	private static final int ATTACK_DMG = 8;
	
	private static final int UPGRADE_PLUS_DMG = 2;
	
	private static final int CNT = 5;
	private static final int ENERGY_GAIN = 1;
	private static final int DRAW_CNT = 1;
	private static final int UPGRADE_PLUS_DRAW_CNT = 1;
	
	public SuperIceKick() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.block = this.baseBlock = CNT;
		this.magicNumber = this.baseMagicNumber = DRAW_CNT;
//		this.damageType = DamageInfo.DamageType.NORMAL;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		super.triggerOnGlowCheck();
		
		if (AbstractDungeon.player.drawPile.size() <= this.block)
			this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
	}
	
	@Override
	public void applyPowersToBlock() {
		this.block = this.baseBlock = CNT;
		this.isBlockModified = false;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
		
		if (p.drawPile.size() <= this.block) {
			this.addToBot(new GainEnergyAction(ENERGY_GAIN));
			this.addToBot(new DrawCardAction(this.magicNumber));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SuperIceKick();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.upgradeMagicNumber(UPGRADE_PLUS_DRAW_CNT);
			this.initializeDescription();
		}
	}
}