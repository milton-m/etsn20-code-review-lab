package search;

import java.io.FileReader;
import java.io.LineNumberReader;

public class Search {
	public static void main(String[] args) {
		try {
			LineNumberReader lnr = new LineNumberReader(new FileReader(args[2]));
			String line;
			while ((line = lnr.readLine()) != null) {
				if (line.contains(args[1])) {
					System.out.println(lnr.getLineNumber());
					// System.out.println(line); //option for writing out line (commented out to not not fail test suite)
				}
			}
			lnr.close();
		} catch (Exception e) {
			System.err.println(e.getClass() + "\nPlease provide input on format "
					+ "\"search cat demo.txt\", and make sure file exists");
		}
	}

}
