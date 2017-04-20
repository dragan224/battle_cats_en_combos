package catcombo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Cat {
	private int id;
	private ArrayList<String> names = new ArrayList<>();
	
	Cat(String line) {
		String tokens[] = line.split(",");
    		this.id = Integer.parseInt(tokens[0]);
    		for (int i = 1; i < tokens.length; i++) {
			names.add(tokens[i]);
		}
	}

	public boolean equals(String other_name) {
		if (other_name == null) return false;
		String other_name_char_only = other_name.replaceAll("[^A-Za-z]+", "");
		
		for (String name: names) {
			String name_char_only = name.replaceAll("[^A-Za-z]+", "");
			if (name_char_only.toLowerCase().equals(other_name_char_only.toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getName(int idx) {
		return names.get(idx);
	}
}
