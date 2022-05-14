package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.CirnoExhaustSpecificCardAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IceDumplings extends AbstractCirnoCard {
	
	public static final String ID = IceDumplings.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 4;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	public IceDumplings() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.NONE
		);
		
		this.block = this.baseBlock = BLOCK;
//		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		Map<String, ArrayList<AbstractCard>> map = new HashMap<>();
		
		for (AbstractCard card : p.hand.group) {
			if (card == this)
				continue;
			
			String key = card.cardID;
			
			if (!map.containsKey(key))
				map.put(key, new ArrayList<>());
			
			map.get(key).add(card);
		}
		
		ArrayList<AbstractCard> res = new ArrayList<>();
		
		for (String key : map.keySet()) {
			ArrayList<AbstractCard> tmp = map.get(key);
			res.add(tmp.get(AbstractDungeon.miscRng.random(0, tmp.size() - 1)));
		}
		
		while (!res.isEmpty()) {
			int k = AbstractDungeon.miscRng.random(0, res.size() - 1);
			this.addToBot(new CirnoExhaustSpecificCardAction(res.get(k), p.hand));
			res.remove(k);
			
			this.addToBot(new GainBlockAction(p, p, this.block));
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceDumplings();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.initializeDescription();
		}
	}
}