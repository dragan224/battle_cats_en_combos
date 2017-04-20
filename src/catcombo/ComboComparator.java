package catcombo;

import java.util.Comparator;

public class ComboComparator implements Comparator<Combo> {

	private String name;
	
	public ComboComparator(String name) {
		super();
		this.name = name;
	}

	@Override
	public int compare(Combo A, Combo B) {
		int effect1 = A.effect();
		int effect2 = B.effect();
		
		if (effect1 < effect2) {
			return -1;
		} else if (effect1 > effect2) {
			return 1;
		} else {
			int strength_a = A.strength();
			int strength_b = B.strength();
			if (strength_a < strength_b) {
				return -1;
			} else if (strength_a > strength_b) {
				return 1;
			}
		}
		return 0;
	}

}
