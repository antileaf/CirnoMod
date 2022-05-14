package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@Deprecated
public class IceKick extends AbstractCirnoCard {
	
	public static final String ID = IceKick.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	
	private static final int ATTACK_DMG = 9;
	private static final int UPGRADE_PLUS_ATTACK_DMG = 3;
	private static final int RETURN_DAMAGE = 3;
	private static final int UPGRADE_PLUS_RETURN_DAMAGE = -1;
	
	public IceKick() {
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
		this.magicNumber = this.baseMagicNumber = RETURN_DAMAGE;
//		this.damageType = DamageInfo.DamageType.NORMAL;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new DamageAction(p, new DamageInfo(p,
				this.magicNumber, DamageInfo.DamageType.THORNS)));
		this.addToBot(new DamageAction(m, new DamageInfo(p,
				this.damage, this.damageTypeForTurn)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceKick();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
			this.upgradeMagicNumber(UPGRADE_PLUS_RETURN_DAMAGE);
			this.initializeDescription();
		}
	}
}