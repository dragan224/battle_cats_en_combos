package catcombo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class Combo {
	private ArrayList<Cat> cats;
	private ArrayList<Integer> forms;
	private int effect;
	private int strength;
	
	public int strength() {
		return strength;
	}
	
	public boolean containsEffect(String effect) {
		if (effect.equals("All")) return true;
		if (Matcher.effects == null || !Matcher.effects.containsKey(this.effect)) return false;
		return Matcher.effects.get(this.effect).equals(effect);
	}
	
	public boolean containsStrength(String strength) {
		if (strength.equals("All")) return true;
		if (Matcher.strengths == null || !Matcher.strengths.containsKey(this.strength)) return false;
		return Matcher.strengths.get(this.strength).equals(strength);
	}
	
	public Combo(ArrayList<Cat> cats, ArrayList<Integer> forms, int effect, int strength) {
		super();
		this.cats = cats;
		this.forms = forms;
		this.effect = effect;
		this.strength = strength;
	}
	
	public int contains(String name) {
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).equals(name)) {
				return forms.get(i);
			}
		}
		return -1;
	}


	public static ArrayList<Combo> parseCombos(String combo_file_name, String cat_file_name) {
		HashMap<Integer, String> cats = new HashMap<>();
		try {
			InputStream input = (InputStream) ClassLoader.class.getResourceAsStream("/" + cat_file_name);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = br.readLine();

			while (line != null) {
				cats.put(Integer.parseInt(line.split(",")[0]), line);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		ArrayList<Combo> combos = new ArrayList<>();
		try {
			InputStream input = (InputStream) ClassLoader.class.getResourceAsStream("/" + combo_file_name);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = br.readLine();

			while (line != null) {
		    	
				String tokens[] = line.split(",");

				ArrayList<Cat> cats_in_combo = new ArrayList<>();
				ArrayList<Integer> forms_in_combo = new ArrayList<>();
				int effect = Integer.parseInt(tokens[tokens.length - 3]);
				int strength = Integer.parseInt(tokens[tokens.length - 2]);
		    	
				for (int i = 2; i <= 10; i += 2) {
		    		int cat = Integer.parseInt(tokens[i]);
		    		int form = Integer.parseInt(tokens[i+1]);
		    		
		    		if (cat != -1) {
		    			cats_in_combo.add(new Cat(cats.get(cat)));
		    			forms_in_combo.add(form);
		    		}
		    	}
		    	assert(forms_in_combo.size() == cats_in_combo.size());
		    	
		    	combos.add(new Combo(cats_in_combo, forms_in_combo, effect, strength));
		    	
		        line = br.readLine();
		    }
		    br.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return combos;
	}


	public String getCatNames() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cats.size(); i++) {
			sb.append(cats.get(i).getName(forms.get(i)));
			if (i != cats.size() - 1) sb.append(", ");
		}
		return sb.toString();
	}
	
	public String getEffect() {
		if (Matcher.effects == null || !Matcher.effects.containsKey(effect)) {
			return "Could not find effect";
		} else {
			return Matcher.effects.get(effect);
		}
	}
	
	public String getStrength() {
		if (Matcher.strengths == null || !Matcher.strengths.containsKey(strength)) {
			return "Could not find strength";
		} else {
			return Matcher.strengths.get(strength);
		}
	}
	
	@Override
	public String toString() {
		return getCatNames() + " - " + getEffect() + "(" + getStrength() + ")";
	}
}
