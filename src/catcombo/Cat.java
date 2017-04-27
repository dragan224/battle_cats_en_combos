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
	
	private String normalize(String name) {
		return name.replaceAll("[^A-Za-z]+", "").toLowerCase();
	}

	public boolean equals(String other_name) {
		if (other_name == null) return false;

		for (String name: names) {
			if (normalize(name).contains(normalize(other_name))) {
				return true;
			}
		}
		return false;
	}
	
	public int minDist(String other_name) {
		int minDist = Integer.MAX_VALUE;
		for (String name: names) {
			minDist = Math.min(minDist, EditDistance.minDistance(
						normalize(name), 
						normalize(other_name)));
		}
		return minDist;
	}
	
	public String getName(int idx) {
		return names.get(idx);
	}
}
