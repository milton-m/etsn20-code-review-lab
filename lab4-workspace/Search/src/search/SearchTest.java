package search;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.PrintStream;

public class SearchTest {
	private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
	private final PrintStream stdOut = System.out;
	private final PrintStream stdErr = System.err;
	
	@BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(baos));
        System.setErr(new PrintStream(baos));
    }
	
	@AfterEach
    public void TearDown() {
        System.setOut(stdOut);
        System.setErr(stdErr);
    }
	
	@Test
	public void testMultipleLines() {
		File file = new File("res/test-multiple-lines.txt");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter("res/test-multiple-lines.txt");
			writer.write("abc\ncba\nbac\n123abc\nnothere\naabca\nb");
		    writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Search.main(new String[] {"search", "abc", "res/test-multiple-lines.txt"});
		
		assertEquals("1\r\n4\r\n6\r\n", baos.toString()); // \r\n to get Windows-style newline
	}
	
	@Test
	public void testNoLines() {
		File file = new File("res/test-no-lines.txt");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter("res/test-no-lines.txt");
			writer.write("");
		    writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Search.main(new String[] {"search", "abc", "res/test-no-lines.txt"});
		
		assertEquals("", baos.toString());
	}
	
	@Test
	public void testNoFile() {
		Search.main(new String[] {"search", "abc", "res/no-such-file-exists.txt"});
		
		assertEquals("class java.io.FileNotFoundException\nPlease provide input on format "
				+ "\"search cat demo.txt\", and make sure file exists\r\n", baos.toString());
	}
}
