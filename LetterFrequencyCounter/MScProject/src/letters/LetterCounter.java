package letters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LetterCounter {
	private int[][] twoLetterFrequencies;

	private final String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
			"u", "v", "w", "x", "y", "z", " "};
	private int[] abcFrequencies;

	private HashMap<String, Integer> hm;
	int dictionary_size;
	
	public LetterCounter() {
		twoLetterFrequencies = determineFrequenciesTwoLetters("src\\letters\\Set3.txt");
		determineFrequenciesAbc("src\\letters\\Set3.txt");

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

	public void determineFrequenciesAbc(String filename){
		String letter;
		int occurs;
		abcFrequencies = new int[27];
		Scanner s;
		for(int i = 0; i <27; i++) {
			try {
				s = new Scanner(new File(filename));
				occurs = 0;
				letter = abc[i];
				while(s.hasNext()) {
					occurs += countRegEx(s.nextLine().toLowerCase(), letter);
				}
				abcFrequencies[i] = occurs;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
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
						test = " " + s.nextLine().toLowerCase() + " ";
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
	
	
	public int happiness(String currentConfig, String consideredLetter) {
		

		double prevHappy;
		
		if(currentConfig.length() == 2) {
			int first = hm.get(String.valueOf(currentConfig.charAt(0)));
			int second = hm.get(String.valueOf(currentConfig.charAt(1)));			
			prevHappy = twoLetterFrequencies[first][second];		
			return (int) (1000 * (prevHappy/abcFrequencies[hm.get(String.valueOf(consideredLetter))]));	
		}
		else if(currentConfig.length() == 3) {
			int first = hm.get(String.valueOf(currentConfig.charAt(0)));
			int middle = hm.get(String.valueOf(currentConfig.charAt(1)));
			int last = hm.get(String.valueOf(currentConfig.charAt(2)));

			return (int) (1000* ((twoLetterFrequencies[first][middle] + twoLetterFrequencies[middle][last])/abcFrequencies[middle]));
			
		}
		else {
			throw new IllegalArgumentException("Only Strings with length 2 or 3 allowed");
		}

	}
	
	
	public static void main(String[] args) {
		LetterCounter lc = new LetterCounter();
		String let = "a";
		System.out.println("//////////////////---------------AAAAAAAAAAAAAAAAAAAAAA--------------------\\\\\\\\\\\\\\\\\\");
		System.out.println("PREVIOUS---------------------------------------------------------------------------");
		System.out.println("0  : " + lc.happiness("aa", let));
		System.out.println("1  : " + lc.happiness("ba", let));
		System.out.println("2  : " + lc.happiness("ca", let));
		System.out.println("3  : " + lc.happiness("da", let));
		System.out.println("4  : " + lc.happiness("ea", let));
		System.out.println("5  : " + lc.happiness("fa", let));
		System.out.println("6  : " + lc.happiness("ga", let));
		System.out.println("7  : " + lc.happiness("ha", let));
		System.out.println("8  : " + lc.happiness("ia", let));
		System.out.println("9  : " + lc.happiness("ja", let));
		System.out.println("10 : " + lc.happiness("ka", let));
		System.out.println("11 : " + lc.happiness("la", let));
		System.out.println("12 : " + lc.happiness("ma", let));
		System.out.println("13 : " + lc.happiness("na", let));
		System.out.println("14 : " + lc.happiness("oa", let));
		System.out.println("15 : " + lc.happiness("pa", let));
		System.out.println("16 : " + lc.happiness("qa", let));
		System.out.println("17 : " + lc.happiness("ra", let));
		System.out.println("18 : " + lc.happiness("sa", let));
		System.out.println("19 : " + lc.happiness("ta", let));
		System.out.println("20 : " + lc.happiness("ua", let));
		System.out.println("21 : " + lc.happiness("va", let));
		System.out.println("22 : " + lc.happiness("wa", let));
		System.out.println("23 : " + lc.happiness("xa", let));
		System.out.println("24 : " + lc.happiness("ya", let));
		System.out.println("25 : " + lc.happiness("za", let));
		System.out.println("26 : " + lc.happiness(" a", let));
		
		System.out.println("\n\n\nSUBSEQUENT--------------------------------------------------------------------------");
		System.out.println("0  : " + lc.happiness("aa", let));
		System.out.println("1  : " + lc.happiness("ab", let));
		System.out.println("2  : " + lc.happiness("ac", let));
		System.out.println("3  : " + lc.happiness("ad", let));
		System.out.println("4  : " + lc.happiness("ae", let));
		System.out.println("5  : " + lc.happiness("af", let));
		System.out.println("6  : " + lc.happiness("ag", let));
		System.out.println("7  : " + lc.happiness("ah", let));
		System.out.println("8  : " + lc.happiness("ai", let));
		System.out.println("9  : " + lc.happiness("aj", let));
		System.out.println("10 : " + lc.happiness("ak", let));
		System.out.println("11 : " + lc.happiness("al", let));
		System.out.println("12 : " + lc.happiness("am", let));
		System.out.println("13 : " + lc.happiness("an", let));
		System.out.println("14 : " + lc.happiness("ao", let));
		System.out.println("15 : " + lc.happiness("ap", let));
		System.out.println("16 : " + lc.happiness("aq", let));
		System.out.println("17 : " + lc.happiness("ar", let));
		System.out.println("18 : " + lc.happiness("as", let));
		System.out.println("19 : " + lc.happiness("at", let));
		System.out.println("20 : " + lc.happiness("au", let));
		System.out.println("21 : " + lc.happiness("av", let));
		System.out.println("22 : " + lc.happiness("aw", let));
		System.out.println("23 : " + lc.happiness("ax", let));
		System.out.println("24 : " + lc.happiness("ay", let));
		System.out.println("25 : " + lc.happiness("az", let));
		System.out.println("26 : " + lc.happiness("a ", let));




	}

}
