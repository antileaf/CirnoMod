package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;


public class GridCardSelectScreenFixPatch {
	@SpirePatch(clz = GridCardSelectScreen.class, method = "open",
		paramtypez = {CardGroup.class, int.class, boolean.class, String.class})
	public static class ChooseMultipleCardsFixPatch {
		@SpirePostfixPatch
		public static void Postfix(GridCardSelectScreen instance, CardGroup group,
		                    int numCards, boolean anyNumber, String msg) {
			instance.forClarity = false;
			
			for (AbstractCard card : instance.targetGroup.group)
				card.stopGlowing();
		}
	}
	
	// 修复了只能选一张和一开始所有卡都是亮的这两个bug
	
//	@SpirePatch(clz = GridCardSelectScreen.class, method = "callOnOpen")
//	public static class InitiallyAllGlowingFixPatch {
//		@SpirePostfixPatch
//		public static void Postfix(GridCardSelectScreen instance) {
//			for (AbstractCard card : instance.targetGroup.group)
//				card.stopGlowing();
//		}
//	}
	
//	@SpirePatch(clz = GridCardSelectScreen.class, method = "hideCards")
//	public static class InitiallyAllGlowingFixPatch {
//		@SpirePostfixPatch
//		public static void Postfix(GridCardSelectScreen instance) {
//			for (AbstractCard card : instance.targetGroup.group)
//				card.darken(true);
//		}
//	}
}
