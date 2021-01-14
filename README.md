## mobile-text-mapper

# Problem Statement
  
  Please read all given below given instruction and consider all scenarios to get the best score*

		Write a program that takes a number and produces all possible phrases consisting of words that match
		this number while using all digits and inserting spaces between words as needed.
		 
		Use a digit to char mapping printed on the telephone keyboards and a word list as a dictionary
		(e.g. http://www.mieliestronk.com/corncob_lowercase.txt). Digit to char mapping is (like on the
		standard phone):
		 
		2 =&gt; [ ‘a’, ‘b’, ‘c’ ]
		3 =&gt; [ ‘d’, ‘e’, ‘f’ ]
		...
		9 =&gt; [ ‘w’, ‘x’, ‘y’, ‘z’ ]
		 
		For example, if input is “56835282” and dictionary contains words “love” and “java” and “lava”, the
		output must contain strings “love java” and “love lava”.
		Please provide a full solution. Please pay close attention to the performance. The code must work fast
		and be scalable. Please test on a long number (for instance 20 digits) and measure time.
    
    
  
# Solution

Solution involves dynamic programming to reduce recursion calls and uses streams and filters to reduce the huge data strucutre that can be created with a string of length 20+.
  
# Commands

Build:

mvn clean install

Test:

mvn test

Execute: Needs a runtime argument for which possible phrases need to be evaluated.

Example - java -jar target\mobile-text-mapper-0.0.1-SNAPSHOT.jar 56835282

# Requirements

Java 8 and 9 have been used, so please use versions ahead of these. Created with Java 14 jdk.

