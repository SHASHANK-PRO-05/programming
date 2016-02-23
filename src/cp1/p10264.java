package cp1;

import java.util.Scanner;

public class p10264 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int n = scanner.nextInt();
			int size = (int) Math.pow(2, n);
			int arr[] = new int[size];
			for (int i = 0; i < size; i++) {
				arr[i] = scanner.nextInt();
			}
			int[] sum = new int[size];
			for (int i = 0; i < size; i++) {
				int newSum = 0;
				for (int j = 0; j < n; j++) {
					int temp = i ^ (1 << j);
					newSum+= arr[temp];
				}
				sum[i] = newSum;
			}
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < n; j++) {
					int temp = i ^ (1 << j);
					max = Math.max(max, sum[i] + sum[temp]);
				}
			}
			System.out.println(max);
		}
	}
}
