import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class demo {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("demo.txt"));
		
		while(br.ready()) {
			String line = br.readLine();
			if (line.equals("")) {
				System.out.println("空行");
			}
		}
		System.out.println("OK");
		
		br.close();
	}
}
