package com.pearson.coding.challenges;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * @author smitha
 *
 */
public class CSVParsing {

	/**
	 * @param s - string
	 * @throws URISyntaxException 
	 */
	public void parseString(String s) throws URISyntaxException {

		char[] inputArray = s.toCharArray();
		boolean inQuotes = false;
		
		//String desktop = System.getProperty("user.home") + "/Desktop/";
		//File file = new File(desktop+ "CSVParsingOutput.txt");
	
		
		File CSVParsingOutput = new File("src/resources/CSVParsingOutput.txt");
	    //boolean success = newFile.createNewFile();
		
		try {
			//Delete previous file out
			Files.deleteIfExists(CSVParsingOutput.toPath());
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(CSVParsingOutput, true));
			
			for (int i = 0; i < inputArray.length; ++i) {
				if (inputArray[i] == '"') {
					inQuotes = !inQuotes;
				}
				if (!inQuotes && inputArray[i] == ',') {
					writer.newLine();		
				} else {
					writer.write(inputArray[i]);			
				}
			}	
			
			 writer.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	
		try {
			CSVParsing cSVParsing = new CSVParsing();

			//String desktop = System.getProperty("user.home") + "/Desktop/";
			//File file = new File(desktop + "CSVParsingInput.txt");
			
			URL res = CSVParsing.class.getClassLoader().getResource("resources/CSVParsingInput.txt");
			File CSVParsingInput = Paths.get(res.toURI()).toFile();
			String absolutePath = CSVParsingInput.getAbsolutePath();
			
			File file = new File(absolutePath);
			
			
			Scanner scanner = new Scanner(new FileReader(file));

			StringBuilder sb = new StringBuilder();
			while (scanner.hasNext()) {
				sb.append(scanner.next());
			}
			scanner.close();
			String inString = sb.toString();
			System.out.println(inString);
			//Instant start = Instant.now();
			// parse string
			cSVParsing.parseString(inString);

			//Instant end = Instant.now();
			// uncomment this to see time taken to run this program to console
			//System.out.println(Duration.between(start, end));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
