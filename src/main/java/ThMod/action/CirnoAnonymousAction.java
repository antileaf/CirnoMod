//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import java.util.ArrayList;
import java.util.Arrays;

public class CirnoAnonymousAction extends AbstractGameAction {
	ArrayList<Runnable> runnables;
	
	public CirnoAnonymousAction(Runnable... runnables) {
		this.actionType = ActionType.SPECIAL;
		
		this.runnables = new ArrayList<>();
		this.runnables.addAll(Arrays.asList(runnables));
	}
	
	public void update() {
		if (!this.isDone) {
			for (Runnable o : this.runnables)
				if (o != null)
					o.run();
			
			this.isDone = true;
		}
	}
}
