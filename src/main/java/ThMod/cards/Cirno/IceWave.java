package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.IceWaveAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceWave extends AbstractCirnoCard {
	
	public static final String ID = IceWave.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 3;
	private static final int BONUS = 3;
	private static final int UPGRADE_PLUS_BONUS = 1;
	
	public IceWave() {
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
		this.block = this.baseBlock = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BONUS;
	}
	
	public void updateOutOfHand() {
		this.baseDamage = this.baseBlock = ATTACK_DMG + ThMod.iceWaveGet();
		
		this.initializeDescription();
	}
	
	@Override
	public void applyPowers() {
		this.baseDamage = this.baseBlock = ATTACK_DMG + ThMod.iceWaveGet();
//		for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisCombat)
//			if (card instanceof IceWave) {
//				this.baseDamage += card.magicNumber;
//				this.baseBlock += card.magicNumber;
//			}
		super.applyPowers();

		this.initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new GainBlockAction(p, this.block));
		this.addToBot(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		
		this.addToBot(new IceWaveAction(this, this.magicNumber));
//
//		ThMod.iceWaveAdd(this.magicNumber);
		
//		this.addToBot(new ApplyPowerAction(p, p, new IceWavePower(this.magicNumber)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceWave();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_BONUS);
			this.initializeDescription();
		}
	}
}