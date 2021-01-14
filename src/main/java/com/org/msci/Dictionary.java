package com.org.msci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Dictionary {

	private static final String DICTIONARY_URL = "http://www.mieliestronk.com/corncob_lowercase.txt";
	
	public Set<String> populateDictionary() {
		Set<String> dictionary =  new HashSet<>();

		try { 
			URL oracle = new URL(DICTIONARY_URL);
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				dictionary.add(inputLine);
			in.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
		return dictionary;
	}
}
