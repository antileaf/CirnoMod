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

public class Hailstorm extends AbstractCirnoCard {
	
	public static final String ID = "Hailstorm";
	public static final String IMG_PATH = "img/cards/Hailstorm.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MULTIPLIER = 2;
	private static final int UPGRADE_PLUS_MULTIPLIER = 1;
	
	public Hailstorm() {
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
		
		this.magicNumber = this.baseMagicNumber = MULTIPLIER;
	}
	
	@Override
	public void initializeDescription() {
		this.rawDescription = DESCRIPTION;
		if (AbstractDungeon.isPlayerInDungeon() && !AbstractDungeon.player.exhaustPile.isEmpty()) {
			this.rawDescription += " NL " + cardStrings.EXTENDED_DESCRIPTION[0];
		}
		
		super.initializeDescription();
	}
	
	@Override
	public void applyPowers() {
		
		int cnt = AbstractDungeon.player.exhaustPile.size();
		this.damage = this.baseDamage = cnt * this.magicNumber;
		
		super.applyPowers();
		this.isDamageModified = this.damage != this.baseDamage;
		
		initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.applyPowers();
		this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
	}
	
	public AbstractCard makeCopy() {
		return new Hailstorm();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeMagicNumber(UPGRADE_PLUS_MULTIPLIER);
			initializeDescription();
		}
	}
}