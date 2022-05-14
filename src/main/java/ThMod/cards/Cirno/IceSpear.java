package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class IceSpear extends AbstractCirnoCard {
	
	public static final String ID = IceSpear.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	
	private static final int ATTACK_DMG = 8;
	private static final int AMPLIFIED_ATTACK_DMG = 11;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int UPGRADE_PLUS_AMPLIFIED_ATTACK_DMG = 4;
	
	public IceSpear() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = AMPLIFIED_ATTACK_DMG;
	}
	
	@Override
	public void applyPowers() {
		int tmp_damage = this.baseDamage;
		this.baseDamage = this.baseMagicNumber;
		super.applyPowers();
		
		this.initializeDescription();
		
		this.magicNumber = this.damage;
		this.baseDamage = tmp_damage;
		super.applyPowers();
	}
	
	@Override
	public void triggerOnGlowCheck() {
		super.triggerOnGlowCheck();
		
		boolean debuff = false;
		for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
			for (AbstractPower p : m.powers)
				if (p.type == AbstractPower.PowerType.DEBUFF) {
					debuff = true;
					break;
				}
			
			if (debuff)
				break;
		}
		
		if (debuff)
			this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		boolean has_debuff = false;
		for (AbstractPower power : m.powers)
			has_debuff |= (power.type == AbstractPower.PowerType.DEBUFF);
		
		this.addToBot(new DamageAction(m, new DamageInfo(p, (has_debuff ? this.magicNumber : this.damage),
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceSpear();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.upgradeMagicNumber(UPGRADE_PLUS_AMPLIFIED_ATTACK_DMG);
			this.initializeDescription();
		}
	}
}