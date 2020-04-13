package com.pearson.coding.challenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author smitha
 *
 */
public class Anagram {

	/**
	 * @param ps - input string
	 * @param qs - query string
	 * @return - number of anagrams in the given string
	 */
	public int numOfAnagrams(String ps, String qs) {

		// uncomment below line to see length of the parent string
		// System.out.println("Lenght of Parent String " + ps.length());

		if (!validateInputs(ps, qs)) {
			return 0;
		}

		String parentStr = ps.toLowerCase();
		String queryStr = qs.toLowerCase();
		int queryStrLength = queryStr.length();
		Map<Character, Integer> queryMap = getMapForQueryString(queryStr);
		int startIndex = 0;
		int anagramCount = 0;
		int i = 0;
		int j = 0;

		while (i < parentStr.length()) {
			// Loop count
			j++;
			// Temp map of query string to work on
			Map<Character, Integer> tempMap = clone(queryMap);
			// Calculate end index to substring
			int endIndex = startIndex + queryStrLength;
			if (endIndex > parentStr.length()) {
				break;
			}
			// Substring to check for anagram
			String parentSubString = getSubString(parentStr, startIndex, endIndex);

			// Iterate and break when you don't find a matching anagram char
			for (int k = parentSubString.length() - 1; k >= 0; k--) {

				int value = integerToInt(tempMap.get(parentSubString.charAt(k)));

				if (value == -1) {
					startIndex = i + k + 1;
					break;
				} else if (value > 0) {
					tempMap.put(parentSubString.charAt(k), value - 1);
				} else if (value == 0) {
					startIndex = i + ((k == 0) ? 1 : k);
				}
			}

			// count the anagrams
			if (getSumofValuesFromMap(tempMap) == 0) {
				anagramCount = anagramCount + 1;
				i++;
				startIndex = i;
			} else {
				i = startIndex;
			}
		}
		// Uncomment bellow line to see num of loops
		System.out.println("loopcount = " + j);
		return anagramCount;

	}

	/**
	 * @param i - Integer
	 * @return - negative number, -1 if Integer null
	 */
	private int integerToInt(Integer i) {
		return i != null ? i.intValue() : -1;
	}

	/**
	 * @param ps - parent string
	 * @param qs - query string
	 * @return - false if either of the string is null or empty or parent string
	 *         length is less than query string
	 */
	private boolean validateInputs(String ps, String qs) {

		if (ps == null || ps.trim().length() == 0 || qs == null || qs.trim().length() == 0
				|| ps.trim().length() < qs.length()) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * @param tempQueryMap - input map of type Character - keys and Integer - values
	 * @return sum of all values
	 */
	private int getSumofValuesFromMap(Map<Character, Integer> tempQueryMap) {

		int sum = tempQueryMap.values().stream().mapToInt(i -> i).sum();
		return sum;
	}

	/**
	 * @param <Character>
	 * @param <Integer>
	 * @param original    - Original Map
	 * @return - Map cloned copy of original map
	 */
	public <Character, Integer> Map<Character, Integer> clone(Map<Character, Integer> original) {
		return original.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	/**
	 * @param parentString
	 * @param startIndex
	 * @param endIndex
	 * @return - substring based on input indexes
	 */
	private String getSubString(String parentString, int startIndex, int endIndex) {
		String subStr = parentString.substring(startIndex, endIndex);
		return subStr;
	}

	/**
	 * @param queryString - string
	 * @return - Map with characters from string as a keys and count of chars as
	 *         values
	 */
	private Map<Character, Integer> getMapForQueryString(String queryString) {
		Map<Character, Integer> queryMap = new HashMap<Character, Integer>();
		int count = 0;
		for (int i = 0; i < queryString.length(); i++) {

			if (queryMap.get(queryString.charAt(i)) != null) {
				count = (int) queryMap.get(queryString.charAt(i)) + 1;
			} else {
				count = 1;
			}
			queryMap.put(queryString.charAt(i), count);
		}
		return queryMap;
	}

	// Main method to run this program
	public static void main(String[] args) {

		try {

			Anagram anagram = new Anagram();
			// System.out.println(anagram.numOfAnagrams("AdnBndAndBdaBn", "dAn"));
			/*
			 * // Scanner scanner = new Scanner(System.in);
			 * 
			 * // Enter input string and press Enter, sample input parentString =
			 * "AdnBndAndBdaBn" and queryString = "dAn" System.out.
			 * println("Enter parent string press enter to input query string and enter agin to see results "
			 * ); String parentString = scanner.nextLine(); String queryString =
			 * scanner.nextLine(); scanner.close();
			 * 
			 * System.out.println(anagram.numOfAnagrams(parentString, queryString));
			 * 
			 */

			// Uncomment this block to provide string input from a .txt file stored in your
			// desktop - mac

			//String desktop = System.getProperty("user.home") + "/Desktop/";
			
			URL res = Anagram.class.getClassLoader().getResource("resources/AnagramInput.txt");
			File Anagraminput = Paths.get(res.toURI()).toFile();
			String absolutePath = Anagraminput.getAbsolutePath();
			
			File file = new File(absolutePath);
			
			Scanner scanner = new Scanner(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			while (scanner.hasNext()) {
				sb.append(scanner.next());
			}
			scanner.close();
			String outString = sb.toString();

			Instant start = Instant.now(); // prints the number of anagrams to console
			System.out.println(anagram.numOfAnagrams(outString, "cAda"));

			Instant end = Instant.now();
			// uncomment this to see time taken to run this program to console

			//System.out.println(Duration.between(start, end));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
