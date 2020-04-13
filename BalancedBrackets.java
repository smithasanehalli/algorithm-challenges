package com.pearson.coding.challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author smitha
 *
 */
public class BalancedBrackets {
	Map<Character, Character> openClosePair;
	

	/**
	 * @param s - input string
	 * @return - true is the string is balanced
	 */
	public boolean isBalanced(String s) {
		//new stack for comparison
		Stack<Character> stack = new Stack<Character>();
		//Initialize map with brackets for comparison
		openClosePair = new HashMap<Character, Character>();
		openClosePair.put('(',  ')');
		openClosePair.put('{', '}');
		openClosePair.put('[', ']');
	
		//If the string is empty or does not contain any brackets, string is balanced
		if (isEmptyString(s) ) {
			return true;
		}
		
		for (int i = 0; i < s.length(); i++) {

			if (openClosePair.containsKey(s.charAt(i))) {
				stack.push(s.charAt(i));

			} else if (openClosePair.containsValue(s.charAt(i))) {
				if (stack.isEmpty())
					return false;
				if (openClosePair.get(stack.pop()) != s.charAt(i))
					return false;
			}
		}
		return stack.isEmpty();
	}

	/**
	 * @param s - input string
	 * @return - true if the string is empty, else false
	 */
	private boolean isEmptyString(String s) {

		if (s != null && s.isEmpty())
			return true;
		return false;
	}


    //Main method 
	public static void main(String[] args) {
		// sample inputs "[(])"

		Scanner scanner = new Scanner(System.in);

		// Enter input string and press Enter
		System.out.println("Enter input string");
		String userInput = scanner.nextLine();
		scanner.close();
		BalancedBrackets BalancedBrackets = new BalancedBrackets();
		System.out.println("String balanced: " + BalancedBrackets.isBalanced(userInput));

	}
}
