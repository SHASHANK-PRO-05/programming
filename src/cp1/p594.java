package cp1;

import java.util.BitSet;
import java.util.Scanner;

public class p594 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int num = scanner.nextInt();
			BitSet b = new BitSet();
			System.out.println(num + " converts to " + Integer.reverseBytes(num));
		}
	}
}
