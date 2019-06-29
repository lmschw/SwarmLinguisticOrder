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
		twoLetterFrequencies = determineFrequenciesTwoLetters("C:\\Users\\lschw\\Dropbox\\Bham\\Swarm robotics\\LetterFrequencyCounter\\MScProject\\src\\letters\\Set3.txt");
//		threeLetterFrequencies = determineFrequenciesThreeLetters("C:\\Users\\lschw\\Dropbox\\Bham\\Swarm robotics\\LetterFrequencyCounter\\MScProject\\src\\letters\\Set3.txt");
//		dictionary_size = countDicEntries("C:\\Users\\lschw\\Dropbox\\Bham\\Swarm robotics\\LetterFrequencyCounter\\MScProject\\src\\letters\\Set3.txt");
//		determineFrequenciesAbc("C:\\Users\\lschw\\Dropbox\\Bham\\Swarm robotics\\LetterFrequencyCounter\\MScProject\\src\\letters\\Set3.txt");
		
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
	
	public static int countDicEntries(String dictionaryfile) {
		Scanner s;
		String st;
		int no = 0;
		try {
			s = new Scanner(new File(dictionaryfile));
			while(s.hasNext()) {
				st = s.next();
				no++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return no;
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
					occurs += countRegEx(s.nextLine(), letter);
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
	
	
	public int[][][] determineFrequenciesThreeLetters(String filename){
		int[][][] frequencies = new int[27][27][27];
		String combo;
		int occurs;
		Scanner s;
		String test;
		for(int i = 0; i <27; i++) {
			for(int j = 0; j<27; j++) {
				for(int k = 0; k<27; k++) {
					try {
						s = new Scanner(new File(filename));
						occurs = 0;
						combo = abc[i] + abc[j] + abc[k];
						while(s.hasNext()) {
							test = " " + s.nextLine() + " ";
							occurs += countRegEx(test, combo);
						}
						frequencies[i][j][k] = occurs;
						if(occurs > 0) {
							if(abc[i].equals(" ")) {
								combo = "[]" + abc[j] + abc[k];
							}
							else if(abc[k].equals(" ")) {
								combo = abc[i] + abc[j] + "[]";
							}
							System.out.println(combo + " : " + occurs);
						}
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
		return frequencies;
	}
	
	
	public int happiness(String currentConfig) {
		

		int prevHappy;
		int endHappy;
		int initHappy;
		int fullHappy;
		
		if(currentConfig.length() == 2) {
			int first = hm.get(String.valueOf(currentConfig.charAt(0)));
			int second = hm.get(String.valueOf(currentConfig.charAt(1)));
			
			initHappy = twoLetterFrequencies[26][first];
			prevHappy = twoLetterFrequencies[first][second];
			endHappy = twoLetterFrequencies[second][26];
			
//			return (int)((prevHappy/Math.max(abcFrequencies[first], abcFrequencies[second])) * 100);
			return prevHappy;
			
		}
		else if(currentConfig.length() == 3) {
			int first = hm.get(String.valueOf(currentConfig.charAt(0)));
			int middle = hm.get(String.valueOf(currentConfig.charAt(1)));
			int last = hm.get(String.valueOf(currentConfig.charAt(2)));
			
			fullHappy = threeLetterFrequencies[first][middle][last];

			return (int)((fullHappy/dictionary_size) * 100);
			
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



//		System.out.println("[]s : " +lc.happiness(" s"));
//		System.out.println("[]d : " +lc.happiness(" d"));
//		System.out.println("[]a : " +lc.happiness(" a"));
//		System.out.println("[]i : " +lc.happiness(" i"));
//		System.out.println("sa : " +lc.happiness("sa"));
//		System.out.println("sd : " +lc.happiness("sd"));
//		System.out.println("si : " +lc.happiness("si"));
//		System.out.println("da : " +lc.happiness("da"));
//		System.out.println("di : " +lc.happiness("di"));
//		System.out.println("ds : " +lc.happiness("ds"));
//		System.out.println("ai : " +lc.happiness("ai"));
//		System.out.println("ad : " +lc.happiness("ad"));
//		System.out.println("as : " +lc.happiness("as"));
//		System.out.println("ia : " +lc.happiness("ia"));
//		System.out.println("id : " +lc.happiness("id"));
//		System.out.println("is : " +lc.happiness("is"));
//		System.out.println("s[] : " +lc.happiness("s "));
//		System.out.println("d[] : " +lc.happiness("d "));
//		System.out.println("a[] : " +lc.happiness("a "));
//		System.out.println("i[] : " +lc.happiness("i "));









	}

}
