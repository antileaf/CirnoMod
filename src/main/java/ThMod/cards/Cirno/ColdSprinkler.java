package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ColdSprinkler extends AbstractCirnoCard {
	
	public static final String ID = ColdSprinkler.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int MOTIVATION_COST = 1;
	
	public ColdSprinkler() {
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
		this.isMultiDamage = true;
		this.motivationCost = MOTIVATION_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
		
		if (this.isMotivated) {
			if (this.upgraded)
				this.addToBot(new ArmamentsAction(true));
			else {
				int cnt = 1;
				if (p.hasPower("ChillPower"))
					cnt += p.getPower("ChillPower").amount;
				
				for (int i = 0; i < cnt; i++)
					this.addToBot(new UpgradeRandomCardAction());
			}
		}
		else
			this.addToBot(new UpgradeRandomCardAction());
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ColdSprinkler();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}