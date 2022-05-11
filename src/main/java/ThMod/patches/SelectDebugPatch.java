package ThMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;

@Deprecated
@SpirePatch(
		clz = GridCardSelectScreen.class,
		method = "update"
)
public class SelectDebugPatch {
	
//	public static void Prefix(GridCardSelectScreen __instance) throws NoSuchFieldException, IllegalAccessException {
//		Field f1 = __instance.getClass().getDeclaredField("numCards");
//		f1.setAccessible(true);
//		Field f2 = __instance.getClass().getDeclaredField("cardSelectAmount");
//		f2.setAccessible(true);
//		ThMod.logger.info("DEBUG  pre max = " + f1.getInt(__instance) +
//				"  now = " + f2.getInt(__instance));
//	}
	
//	public static void Postfix(GridCardSelectScreen __instance) throws NoSuchFieldException, IllegalAccessException {
//		Field f = __instance.getClass().getDeclaredField("numCards");
//		f.setAccessible(true);
//		ThMod.logger.info("DEBUG  post num = " + f.getInt(__instance));
////		ThMod.logger.info("                   DEBUG  post num = " +
////				__instance.getClass().getDeclaredField("numCards").toString());
//	}
}
