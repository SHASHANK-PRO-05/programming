import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class testMaker {
	public static void main(String[] args) throws FileNotFoundException {
		int t = 100000;
		PrintWriter pw = new PrintWriter("text.txt");
		pw.println(t);
		while (t-- != 0) {
			int caseQ = (int) (Math.random() * 5);
			if (caseQ == 0)
				caseQ++;
			if (caseQ == 5)
				caseQ--;
			if (caseQ == 1 || caseQ == 2) {
				pw.println(caseQ + " " + (int) (Math.random() * 100000));
			} else {
				pw.println(caseQ);
			}
		}
		pw.flush();
		pw.close();
	}
}