package com.org.msci;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CharacterNumberMapping { 
	static final Map<Integer, List<Character>> phoneMapping = Map.of(
			0, Arrays.asList('\0'),
			1, Arrays.asList('\0'),
			2, Arrays.asList('a','b','c'),
			3, Arrays.asList('d','e','f'),
			4, Arrays.asList('g','h','i'),
			5, Arrays.asList('j','k','l'),
			6, Arrays.asList('m','n','o'),
			7, Arrays.asList('p','q','r','s'),
			8, Arrays.asList('t','u','v'),
			9, Arrays.asList('w','x','y','z')
			);

}