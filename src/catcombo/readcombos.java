package catcombo;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

class catcombo {
	int name;
	int unit1;
	int unit1form;
	int unit2;
	int unit2form;
	int unit3;
	int unit3form;
	int unit4;
	int unit4form;
	int unit5;
	int unit5form;
	int type;
	int strength;
	
	catcombo(int name, int unit1, int unit1form, int unit2, int unit2form, int unit3, int unit3form, int unit4, int unit4form, int unit5, int unit5form, int type, int strength) {
		this.name      = name;
		this.unit1     = unit1;
		this.unit1form = unit1form;
		this.unit2     = unit2;
		this.unit2form = unit2form;
		this.unit3     = unit3;
		this.unit3form = unit3form;
		this.unit4     = unit4;
		this.unit4form = unit4form;
		this.unit5     = unit5;
		this.unit5form = unit5form;	
		this.type      = type;
		this.strength  = strength;
	}//constructor for combo
	
}//comboclass

class names {
	int ID;
	String name1;
	String name2;
	String name3;
	
	names(int ID, String name1, String name2, String name3) {
		this.ID = ID;
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
	}//constructor for names
	
}//namesclass

public class readcombos {
	
public static void printcombofile(catcombo mycat[], int numofcombos) {
	int i;
	catcombo temp;
	
	for (i = 0; i < numofcombos - 1; i++) {
		temp = mycat[i];
		System.out.println(temp.name  + " " + 
						   temp.unit1 + " " + temp.unit1form + " " +
						   temp.unit2 + " " + temp.unit2form + " " +
						   temp.unit3 + " " + temp.unit3form + " " +
						   temp.unit4 + " " + temp.unit4form + " " +
						   temp.unit5 + " " + temp.unit5form + " " +
						   temp.type  + " " + temp.strength);
	}
} //printcombofile

public static void printnamesfile(names myname[], int numofnames) {
	int i;
	names temp;
	
	for (i = 0; i < numofnames - 1; i++) {
		temp = myname[i];
		System.out.println(temp.ID + " " + temp.name1 + " " + temp.name2 + " " + temp.name3);
	}
} //printcombofile

	static int getInt() {
		//This method takes in user input
		Scanner user = new Scanner(System.in);
		//Creating scanner
		while (!user.hasNextInt()) {
			//Asking for input
			System.out.println("Please enter a integer.");
			user.next();
		}
		
		int k = user.nextInt();
		//Take in user input
		return k;
		//Returns the input
	}
	
public static String getNames(names myname[], int numofnames, int nameID, int nameform) {
	int i;
	String returnname = "";
	names temp;
	
	for (i = 0; i < numofnames - 1; i++) {
		temp = myname[i];
		
		if (nameID == -1) {
			returnname = "";
		} else if (temp.ID == nameID) {
			i = numofnames;
			
			if (nameform == 0) {
				returnname = temp.name1 + ", ";
			} else if (nameform == 1) {
				returnname = temp.name2 + ", ";
			} else {
				returnname = temp.name3 + ", ";
			}
			
		}
		
	}
	
	return returnname;
} //getNames

public static int[] fromNames(names myname[], int numofnames, String fullname) {
	int i = 0;
	boolean success = false;
	int ar[] = new int[2];
	names temp;
	
	while (!success && i < numofnames - 1) {
		temp = myname[i];
		
		if (Objects.equals(temp.name1, fullname)) {
			success = true;
			ar[0] = temp.ID;
			ar[1] = 0;
		} else if (Objects.equals(temp.name2, fullname)) {
			success = true;
			ar[0] = temp.ID;
			ar[1] = 1;
		} else if (Objects.equals(temp.name3, fullname)) {
			success = true;
			ar[0] = temp.ID;
			ar[1] = 2;
		}
		
		i++;
	}
	
	if (success == false)  {
		ar[0] = -1;
	}
	
	return ar;
} //fromNames

 public static void main(String args[]) {

    int i, success;
	
	catcombo mycat[] = new catcombo[200];
	int numofcombos = 0;
	
	byte inbuf[] = new byte[20500];     //Input buffer for the whole file
	String str = new String();
	
	int comboID;
	int combo1;
	int combo1form;
	int combo2;
	int combo2form;
	int combo3;
	int combo3form;
	int combo4;
	int combo4form;
	int combo5;
	int combo5form;
	int combotype;
	int combostrength;
	
	Boolean notfinished = true;

    try (DataInputStream dataIn = 
		new DataInputStream(new FileInputStream("NyancomboData.txt"))) {
			success = dataIn.read(inbuf);
			
			if (success != -1) {
				
				for (i = 0; i < success; i++)
				str += (char) inbuf[i];
				
			}
			
			while ((str != "") & notfinished) {
				
				String[] split = str.split(",");
				//Get the combo ID
				comboID = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Skip over the unlock requirements (for now)
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 1st unit ID
				combo1 = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 1st unit form
				combo1form = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 2nd unit ID
				combo2 = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 2nd unit form
				combo2form = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 3rd unit ID
				combo3 = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 3rd unit form
				combo3form = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 4th unit ID
				combo4 = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 4th unit form
				combo4form = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 5th unit ID
				combo5 = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the 5th unit form
				combo5form = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the effect of combo
				combotype = Integer.parseInt(str.substring(0, str.indexOf(",")));
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Get the strength of combo
				combostrength = Integer.parseInt(str.substring(0, str.indexOf(","))); 
				
				str = str.substring(str.indexOf(",") + 1, str.length());
				
				//Skip to the linefeed
				
				notfinished = str.indexOf('\n') > 0;
				str = str.substring(str.indexOf('\n')+1, str.length());
				str.trim();
				
				mycat[numofcombos] = new catcombo(comboID, combo1, combo1form, combo2, combo2form, combo3, combo3form, combo4, combo4form, combo5, combo5form, combotype, combostrength);
				numofcombos++;
		}; //while
              
    } catch (IOException exc) {
        System.out.println("Read error.");
        return;
    }

	names myname[] = new names[500];
	int numofnames = 0;
	
	byte inbuf1[] = new byte[20500];     //Input buffer for the whole file
	String str1 = new String();
	
	int unitID;
	String form1name;
	String form2name;
	String form3name;
	
	notfinished = true;

    try (DataInputStream dataIn = 
		new DataInputStream(new FileInputStream("Names.txt")))
 		{
			success = dataIn.read(inbuf1);
			int counter = 0;
			
			if (success == 44) {
				counter++;
			}
			
			if (counter != 4) {
				
				for (i = 0; i < success; i++) {
				
					if (inbuf1[i] != 63) {
						str1 += (char) inbuf1[i];
					}
					
				}
				
			} else {
				counter = 0;
			}
			
			while ((str1 != "") & notfinished) {
				//Get the unit ID
				unitID = Integer.parseInt(str1.substring(0, str1.indexOf(",")));
				
				str1 = str1.substring(str1.indexOf(",") + 1, str1.length());
				
				//Get the 1st form name
				form1name = str1.substring(0, str1.indexOf(","));
				
				str1 = str1.substring(str1.indexOf(",") + 1, str1.length());
				
				//Get the 2nd form name
				form2name = str1.substring(0, str1.indexOf(","));
				
				str1 = str1.substring(str1.indexOf(",") + 1, str1.length());
				
				if (str1 == ",") {
					form3name = "-1";
				} else {
					//Get the 3rd form name
					form3name = str1.substring(0, str1.indexOf(","));
				}

				//Skip to the linefeed
				
				notfinished = str1.indexOf('\n') > 0;
				str1 = str1.substring(str1.indexOf('\n')+1, str1.length());
				str1.trim();
				
				if (str1 == ":") {
					notfinished = false;
				}
				
				myname[numofnames] = new names(unitID, form1name, form2name, form3name);
				numofnames++;
		}; //while
              
    } catch (IOException exc) {
        System.out.println("Names - Read error.");
        return;
    }

	//printcombofile(mycat,numofcombos);
	//printnamesfile(myname, numofnames);
	
	System.out.println("See all combos with a unit: 1");
	System.out.println("See all combos of a certain effect: 2");
	System.out.println("See all combos of a certain strength: 3");
	
	int input = getInt();
	
	while (input < 0 || input > 3) {
		System.out.println("Invalid input");
		input = getInt();
	}
	
	if (input == 1) {
		System.out.println("Enter in a unit name. Use its farthest form.");
		Scanner in = new Scanner(System.in);
		String inputstring = in.nextLine();
		
		int[] inputarray;
		inputarray = new int[2];
		inputarray = fromNames(myname, numofnames, inputstring);
		input = inputarray[0];
		
		while (input == -1) {
			System.out.println("Invalid input");
			inputstring = in.nextLine();
			
			inputarray = fromNames(myname, numofnames, inputstring);
			input = inputarray[0];
		}
		
		int form = inputarray[1];
		
		for (i = 0; i < numofcombos - 1; i++) {
			catcombo temp;
			temp = mycat[i];
			success = 0;
			
			if ((input == temp.unit1) && (form >= temp.unit1form)) {
				success = 1;
			} else if ((input == temp.unit2) && (form >= temp.unit2form)) {
				success = 1;
			} else if ((input == temp.unit3) && (form >= temp.unit3form)) {
				success = 1;
			} else if ((input == temp.unit4) && (form >= temp.unit4form)) {
				success = 1;
			} else if ((input == temp.unit5) && (form >= temp.unit5form)) {
				success = 1;
			}
			
			if (success == 1) {
				System.out.println(temp.name  + " " + getNames(myname, numofnames, temp.unit1, temp.unit1form) +
								   getNames(myname, numofnames, temp.unit2, temp.unit2form) + getNames(myname, numofnames, temp.unit3, temp.unit3form) +
								   getNames(myname, numofnames, temp.unit4, temp.unit4form) + getNames(myname, numofnames, temp.unit5, temp.unit5form) +
								   temp.type  + " " + temp.strength);
			}
			
		}
		
	} else if (input == 2) {
		System.out.println("Enter in the combo effect");
		input = getInt();
		
		while (input < 0 || input > 22) {
			System.out.println("Invalid input");
			input = getInt();
		}
		
		for (i = 0; i < numofcombos - 1; i++) {
			catcombo temp;
			temp = mycat[i];
			success = 0;
			
			if (input == temp.type) {
				success = 1;
			}
			
			if (success == 1) {
				System.out.println(temp.name  + " " + getNames(myname, numofnames, temp.unit1, temp.unit1form) +
								   getNames(myname, numofnames, temp.unit2, temp.unit2form) + getNames(myname, numofnames, temp.unit3, temp.unit3form) +
								   getNames(myname, numofnames, temp.unit4, temp.unit4form) + getNames(myname, numofnames, temp.unit5, temp.unit5form) +
								   temp.type  + " " + temp.strength);
			}
			
		}
		
	} else if (input == 3) {
		System.out.println("Enter in the combo strength");
		input = getInt();
		
		while (input < 0 || input > 3) {
			System.out.println("Invalid input");
			input = getInt();
		}
		
		for (i = 0; i < numofcombos - 1; i++) {
			catcombo temp;
			temp = mycat[i];
			success = 0;
			
			if (input == temp.strength) {
				success = 1;
			}
			
			if (success == 1) {
				System.out.println(temp.name  + " " + getNames(myname, numofnames, temp.unit1, temp.unit1form) +
								   getNames(myname, numofnames, temp.unit2, temp.unit2form) + getNames(myname, numofnames, temp.unit3, temp.unit3form) +
								   getNames(myname, numofnames, temp.unit4, temp.unit4form) + getNames(myname, numofnames, temp.unit5, temp.unit5form) +
								   temp.type  + " " + temp.strength);
			}
			
		}
		
	}
	
 }//main

}//num