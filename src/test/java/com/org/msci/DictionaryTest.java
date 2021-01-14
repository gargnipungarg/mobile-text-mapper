package com.org.msci;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DictionaryTest {
	
	@Test
	void populateDictionaryTest() {
		Set<String> dictionary =  new HashSet<>();
		try { 
			URL oracle = new URL("http://www.mieliestronk.com/corncob_lowercase.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				dictionary.add(inputLine);
			in.close();
			if(dictionary.size() == 0) {
				fail("Dictionary is empty.");
			}
		}catch(Exception e) {
			fail("Dictionary is offline.");
		}
	}

}
