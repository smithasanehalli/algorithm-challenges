package com.pearson.coding.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author smitha
 *
 */
public class TimeConfusion {

	/**
	 * result of recursion;
	 */
	public String result = null;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @param vars
	 */
	public void calculateResult(String[] vars) {

		for (int i = 0; i < vars.length; i++) {

			getCorrectTime(inputToTimeObj(vars[i]), 0);

			System.out.println(getResult() != null ? "The correct time is " + getResult() : "Look at the sun");

			setResult(null);
		}

	}

	/**
	 * @param s - String of times input
	 * @return
	 */
	private List<Time> inputToTimeObj(String s) {
		String[] splitStr = s.trim().split("\\s+");
		List<Time> times = new ArrayList<Time>(splitStr.length);
		for (int i = 0; i < splitStr.length; i++) {
			String[] time = splitStr[i].split(":");
			times.add(new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), "AM"));
		}

		return times;

	}

	/**
	 * @param times - list of times
	 * @param i
	 */
	public void getCorrectTime(List<Time> times, int i) {
		if (result != null)
			return;

		if (i == times.size()) {
			String res = Calculate(times);
			setResult(res);
			return;
		}

		times.get(i).setTt("AM");
		getCorrectTime(times, i + 1);

		times.get(i).setTt("PM");
		getCorrectTime(times, i + 1);
	}

	private String Calculate(List<Time> times) {
		for (int i = 0; i < times.size(); i++) {
			times.get(i).calculateTotalMins();
		}

		List<Time> cloned_list = new ArrayList<Time>(times);

		Collections.sort(cloned_list);

		// considering a, b, c as the sorted times with a as the lowest
		// (a+c) / 2 = b
		if (((cloned_list.get(0).getTotalmins() + cloned_list.get(2).getTotalmins()) / 2) == cloned_list.get(1)
				.getTotalmins()) {
			return "" + cloned_list.get(1).hour + " : " + cloned_list.get(1).minute;
		} else {

			return null;
		}

	}

	//Main to execute the program
	public static void main(String[] args) {

		System.out.println("Enter number of cases.");

		try {
			Scanner scanner = new Scanner(System.in);
			int numCases = scanner.nextInt();

			String[] vars = new String[numCases];

			scanner.nextLine();
			for (int i = 0; i < vars.length; i++) {
				System.out.println("Enter three times separated by single space characters: H1:M1 H2:M2 H3:M3");
				vars[i] = scanner.nextLine();
			}
			scanner.close();
			TimeConfusion timeConfusion = new TimeConfusion();
			timeConfusion.calculateResult(vars);
		} catch (Exception e) {
			System.out.println("Invalid input");
			System.exit(0);
		}
		

		
		

	}

}

/**
 *custom Time class
 *
 */
class Time implements Comparable<Time> {

	@Override
	public String toString() {
		return "Time [hour=" + hour + ", minute=" + minute + ", tt=" + tt + ", totalmins=" + totalmins + "]";
	}

	public int hour;
	public int minute;
	public String tt;

	public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public Integer totalmins;

	public int getTotalmins() {
		return this.totalmins;
	}

	public Time(int hour, int minute, String tt) {
		this.hour = hour;
		this.minute = minute;
		this.tt = tt;
	}

	public void calculateTotalMins() {
		totalmins = getTotalMins();
	}

	private int getTotalMins() {
		if (tt == "AM") {
			if (hour == 12)
				return minute;

			return (hour * 60) + minute;
		} else {
			if (hour == 12)
				return (12 * 60) + minute;

			return ((hour + 12) * 60) + minute;
		}
	}

	@Override
	public int compareTo(Time o) {

		return this.totalmins.compareTo(o.getTotalmins());
	}

}
