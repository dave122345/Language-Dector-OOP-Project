package ie.gmit.sw;

/**
 *  @author David Nolan G00363191
 * @version 1.0
 * @return 
 * @since 1.8
 * 
 * <b>Runner class</b> used to start the program.
 */

import java.io.*;
import java.util.*;

public class Runner {

	private static Scanner sc = new Scanner(System.in);

	private static String languageFile;
	private static String queryFile;

	public static void main(String[] args) throws Throwable {

		
		setLanguageFile();
		setQueryFile();
		

		Parser p = new Parser(getLanguageFile(), 5);// get language file, no. kmers

		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread(p);
		//System.out.println("start");
		t.start();
		//System.out.println("join");
		t.join();
		System.out.println("loading and processing...");
		db.resize(300);

		p.analyseQuery(getQueryFile());
	}

	// set language file access
	public static void setLanguageFile() {
		String fileInput = "";
		boolean fileExist = false;

		while (!fileExist) {
			System.out.println("please enter language file name(must include .txt)\n");
			fileInput = sc.next();
			if (new File(fileInput).isFile()) { // checks if file exists
				fileExist = true;
			} else {
				System.out.println("incorrect please try again");
			}
		}
		languageFile = fileInput;
		Runner.languageFile = languageFile;
	}

	// get language access
	public static String getLanguageFile() {
		return languageFile;
	}

	// set query
	public static void setQueryFile() {
		String fileInput = "";
		boolean fileExist = false;

		while (!fileExist) {
			System.out.println("please enter query file name(must include .txt)\n");
			fileInput = sc.next();
			if (new File(fileInput).isFile()) { // checks if file exists
				fileExist = true;
			} else {
				System.out.println("incorrect please try again");
			}
		}
		queryFile = fileInput;
		Runner.queryFile = queryFile;
	}

	// get query
	public static String getQueryFile() {
		return queryFile;
	}

}
