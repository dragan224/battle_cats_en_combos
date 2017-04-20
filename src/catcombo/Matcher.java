package catcombo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Matcher {
	private ArrayList<Combo> combos;
	static HashMap<Integer, String> effects = null;
	static HashMap<Integer, String> strengths = null;
	
	public Matcher(ArrayList<Combo> combos) {
		super();
		this.combos = combos;
		
		if (effects == null) {
			effects = new HashMap<>();
			effects.put(0, "Unit Attack UP");
			effects.put(1, "Unit Defense UP");
			effects.put(2, "Unit Speed UP");
			effects.put(3, "Cannon Start Level UP");
			effects.put(4, "Worker Cat Start Level UP");
			effects.put(5, "Starting Money UP");
			effects.put(6, "Cat Cannon Power UP");
			effects.put(7, "Cat Cannon Recharge UP");
			effects.put(9, "Worker Cat Max UP");
			effects.put(10, "Cat Base Defense UP");
			effects.put(11, "\"Research\" Power UP");
			effects.put(12, "\"Accounting\" Power UP");
			effects.put(13, "\"Study\" Power UP");
			effects.put(14, "\"Strong\" Effect UP");
			effects.put(15, "\"Massive Damage\" Power UP");
			effects.put(16, "\"Resistant\" Effect UP");
			effects.put(17, "\"Knockback\" Effect UP");
			effects.put(18, "\"Slow\" Effect UP");
			effects.put(19, "\"Freeze\" Effect UP");
			effects.put(20, "\"Weaken\" Effect UP");
			effects.put(21, "\"Strengthen\" Effect UP");
		}
		
		if (strengths == null) {
			strengths = new HashMap<>();
			strengths.put(0, "Small");
			strengths.put(1, "Medium");
			strengths.put(2, "Large");
		}
	}
	
	Object[][] search(String name, String effect, String strength) {
		ArrayList<Combo> result = new ArrayList<>();
		for (Combo combo: combos) {
			if (name.equals("") || combo.contains(name) != -1) {
				if (combo.containsEffect(effect) && combo.containsStrength(strength)) {
					result.add(combo);
				}
			}
		}
		Collections.sort(result, new ComboComparator(name));
		Object[][] data = new Object[result.size()][3];
		
		for (int i = 0; i < result.size(); i++) {
			Combo combo = result.get(i);
			data[i][0] = combo.getCatNames();
			data[i][1] = combo.getEffect();
			data[i][2] = combo.getStrength();
		}
		return data;
	}
	
}
