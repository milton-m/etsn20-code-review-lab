package search;

import java.io.FileReader;
import java.io.LineNumberReader;

public class Search {
	public static void main(String[] args) {
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(args[2]));
			String line;
			while ((line = lnr.readLine()) != null) {
				System.out.println(lnr.getLineNumber()); // TODO: only write number if line contains word
			}
		} catch (Exception e) {
			System.err.println(e.getClass() + "\nPlease provide input on format "
					+ "\"search cat demo.txt\", and make sure file exists");
		}
	}

}
