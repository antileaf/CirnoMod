package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Objects;

@Deprecated
public class FreezeActress extends AbstractCirnoCard {
	
	public static final String ID = FreezeActress.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int MOTIVATION_COST = 1;
	
	public FreezeActress() {
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
		
		this.motivationCost = MOTIVATION_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		int cnt = 1 + this.motivatedCnt;
		this.baseDamage = 0;
		
		for (int i = 0; i < cnt; i++) {
			AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK);
			
			while (true) {
				boolean bad = false;
				
				for (AbstractCard c : AbstractDungeon.player.hand.group)
					if (Objects.equals(c.cardID, card.cardID)) {
						bad = true;
						break;
					}
				
				if (!bad)
					break;
				
				card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK);
			}
			
			card = card.makeCopy();
			if (this.upgraded)
				card.upgrade();
			this.baseDamage = Integer.max(this.baseDamage, card.baseDamage);
			
			this.addToBot(new MakeTempCardInHandAction(card));
		}
		
		this.calculateCardDamage(m);
		
		this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		
		this.damage = this.baseDamage = 0;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FreezeActress();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}