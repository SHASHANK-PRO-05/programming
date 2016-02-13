package RandomPractice;

public class PrattandOffice {
	public static void main(String[] args) {
		int t = (int) (Math.random() * 101);
		System.out.println(t);
		for (int i = 0; i < t; i++) {
			int len = (int) (Math.random() * 10000);
			for (int j = 0; j < len; j++) {
				char ch = (char) ((int) (Math.random() * 5) + 'a');
				while (ch < 'a' && ch > 'e') {
					ch = (char) ((int) (Math.random() * 5) + 'a');
				}
				System.out.print(ch);
			}
			System.out.println();
		}

	}
}
