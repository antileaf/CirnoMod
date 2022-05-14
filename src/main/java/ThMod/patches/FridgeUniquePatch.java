package ThMod.patches;

import ThMod.cards.Cirno.Fridge;
import ThMod.cards.Cirno.IceCreamMachine;
import ThMod.cards.Cirno.PerfectSummerIce;
import ThMod.powers.Cirno.FridgePower;
import ThMod.powers.Cirno.PerfectSummerIcePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.IceCream;

import java.util.ArrayList;

public class FridgeUniquePatch {
	
	private static boolean check(AbstractCard card) {
		AbstractPlayer p = AbstractDungeon.player;
		
		if (card instanceof Fridge) {
			boolean bad = p.hasPower(FridgePower.POWER_ID);
			
			if (!bad) {
				ArrayList<ArrayList<AbstractCard>> groups = new ArrayList<>();
				groups.add(p.hand.group);
				groups.add(p.drawPile.group);
				groups.add(p.discardPile.group);
				
				for (ArrayList<AbstractCard> group : groups)
					for (AbstractCard c : group)
						bad |= (c instanceof Fridge);
			}
			
			return bad;
		}
		else if (card instanceof PerfectSummerIce &&
				p.hasPower(PerfectSummerIcePower.POWER_ID) &&
				p.getPower(PerfectSummerIcePower.POWER_ID).amount >= 3)
			return true;
		else if (card instanceof IceCreamMachine) {
			boolean bad = p.hasRelic(IceCream.ID);
			
			if (!bad) {
				ArrayList<ArrayList<AbstractCard>> groups = new ArrayList<>();
				groups.add(p.hand.group);
				groups.add(p.drawPile.group);
				groups.add(p.discardPile.group);
				
				for (ArrayList<AbstractCard> group : groups)
					for (AbstractCard c : group)
						bad |= (c instanceof IceCreamMachine);
			}
			
			return bad;
		}
		else
			return false;
	}
	
	@SpirePatch(clz = AbstractDungeon.class, method = "returnTrulyRandomCardInCombat",
			paramtypez = {})
	public static class FridgeWithoutParamsPatch {
		@SpirePostfixPatch
		public static AbstractCard Postfix(AbstractCard card) {
			return check(card) ? AbstractDungeon.returnTrulyRandomCardInCombat() : card;
		}
	}
	
	@SpirePatch(clz = AbstractDungeon.class, method = "returnTrulyRandomCardInCombat",
			paramtypez = {AbstractCard.CardType.class})
	public static class FridgeWithCardTypePatch {
		@SpirePostfixPatch
		public static AbstractCard Postfix(AbstractCard card, AbstractCard.CardType type) {
			return check(card) ? AbstractDungeon.returnTrulyRandomCardInCombat(type) : card;
		}
	}
}
