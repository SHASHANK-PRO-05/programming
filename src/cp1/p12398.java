package cp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class p12398 {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = 0;
		String s = br.readLine();
		while (s != null) {
			t++;
			StringBuilder st = new StringBuilder();
			System.out.println("Case #" + t + ":");
			int[][] arr = new int[3][3];
			for (int i = 0; i < s.length(); i++) {
				switch (s.charAt(i)) {
				case 'c':
					arr[0][2]++;
					arr[0][1]++;
					arr[1][2]++;
					break;
				case 'a':
					arr[0][0]++;
					arr[1][0]++;
					arr[0][1]++;
					break;
				case 'b':
					arr[0][1]++;
					arr[0][0]++;
					arr[0][2]++;
					arr[1][1]++;
					break;
				case 'd':
					arr[1][0]++;
					arr[0][0]++;
					arr[2][0]++;
					arr[1][1]++;
					break;
				case 'e':
					arr[1][1]++;
					arr[0][1]++;
					arr[1][2]++;
					arr[2][1]++;
					arr[1][0]++;
					break;
				case 'f':
					arr[1][2]++;
					arr[0][2]++;
					arr[2][2]++;
					arr[1][1]++;
					break;
				case 'g':
					arr[2][0]++;
					arr[1][0]++;
					arr[2][1]++;
					break;
				case 'h':
					arr[2][1]++;
					arr[2][0]++;
					arr[2][2]++;
					arr[1][1]++;
					break;
				case 'i':
					arr[2][2]++;
					arr[2][1]++;
					arr[1][2]++;
					break;
				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] %= 10;
					if (j != 2)
						System.out.print(arr[i][j] + " ");
					else
						System.out.println(arr[i][j]);
				}
			}
			s = br.readLine();
		}
	}
}
