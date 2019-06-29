package letters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterCounter {
	private int[][] twoLetterFrequencies;
	private int[][][] threeLetterFrequencies;
	private final String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
			"u", "v", "w", "x", "y", "z", " "};
	private int[] abcFrequencies;
	private HashMap<String, Integer> hm;
	int dictionary_size;
	
	public LetterCounter() {
		twoLetterFrequencies = determineFrequenciesTwoLetters("src\\letters\\Set3.txt");
		
		hm = new HashMap<>();
		hm.put("a", 0);
		hm.put("b", 1);
		hm.put("c", 2);
		hm.put("d", 3);
		hm.put("e", 4);
		hm.put("f", 5);
		hm.put("g", 6);
		hm.put("h", 7);
		hm.put("i", 8);
		hm.put("j", 9);
		hm.put("k", 10);
		hm.put("l", 11);
		hm.put("m", 12);
		hm.put("n", 13);
		hm.put("o", 14);
		hm.put("p", 15);
		hm.put("q", 16);
		hm.put("r", 17);
		hm.put("s", 18);
		hm.put("t", 19);
		hm.put("u", 20);
		hm.put("v", 21);
		hm.put("w", 22);
		hm.put("x", 23);
		hm.put("y", 24);
		hm.put("z", 25);
		hm.put(" ", 26);
	}
	
	public static int countRegEx(String toCheck, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(toCheck);
		int occurrences = 0;
		while (m.find()) {
			occurrences++;
		}
		return occurrences;
	}

	
	public int[][] determineFrequenciesTwoLetters(String filename){
		int[][] frequencies = new int[27][27];
		String combo;
		int occurs;
		Scanner s;
		String test;
		for(int i = 0; i <27; i++) {
			for(int j = 0; j<27; j++) {
				try {
					s = new Scanner(new File(filename));
					occurs = 0;
					combo = abc[i] + abc[j];
					while(s.hasNext()) {
						test = " " + s.nextLine() + " ";
						occurs += countRegEx(test, combo);
					}
					frequencies[i][j] = occurs;
					if(occurs > 0) {
						if(abc[i].equals(" ")) {
							combo = "[]" + abc[j];
						}
						else if(abc[j].equals(" ")) {
							combo = abc[i] + "[]";
						}
//						System.out.println(combo + " : " + occurs);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		return frequencies;
	}
	
	
	public int happiness(String currentConfig) {
		

		int prevHappy;
		int fullHappy;
		
		if(currentConfig.length() == 2) {
			int first = hm.get(String.valueOf(currentConfig.charAt(0)));
			int second = hm.get(String.valueOf(currentConfig.charAt(1)));			
			prevHappy = twoLetterFrequencies[first][second];		
			return prevHappy;	
		}
		else if(currentConfig.length() == 3) {
			int first = hm.get(String.valueOf(currentConfig.charAt(0)));
			int middle = hm.get(String.valueOf(currentConfig.charAt(1)));
			int last = hm.get(String.valueOf(currentConfig.charAt(2)));

			return twoLetterFrequencies[first][middle] + twoLetterFrequencies[middle][last];
			
		}
		else {
			throw new IllegalArgumentException("Only Strings with length 2 or 3 allowed");
		}

	}
	public static void main(String[] args) {
		LetterCounter lc = new LetterCounter();
		System.out.println(lc.happiness("ay"));
		System.out.println(lc.happiness("az"));
		System.out.println(lc.happiness("a "));
		System.out.println(lc.happiness("ax"));










	}

}
