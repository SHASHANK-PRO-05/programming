import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class testMaker {
	public static void main(String[] args) throws FileNotFoundException {
		int t = 100000;
		PrintWriter pw = new PrintWriter("text.txt");
		pw.println(t);
		while (t-- != 0) {
			pw.println((long) (Math.random() * 1000) + " " + (long) (Math.random() * 1e9) + " "
					+ (long) (Math.random() * 1e15));
		}
		pw.flush();
		pw.close();
	}
}