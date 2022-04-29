package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FrostMeteorPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrostMeteor extends AbstractCirnoCard {
	
	public static final String ID = "FrostMeteor";
	public static final String IMG_PATH = "img/cards/FrostMeteor.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_PLUS_ATTACK_DMG = 3;
	private static final int CNT = 1;
	
	public FrostMeteor() {
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
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		
		this.addToBot(new ApplyPowerAction(p, p, new FrostMeteorPower(this.magicNumber)));
	}
	
	public AbstractCard makeCopy() {
		return new FrostMeteor();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
			initializeDescription();
		}
	}
}