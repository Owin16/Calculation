package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWrite {

	public void writeFile(String result) {


		try {
			PrintWriter out = new PrintWriter(new File("/result.txt"));
			out.println(result);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
