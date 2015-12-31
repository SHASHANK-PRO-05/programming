package part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChanduandhisGirlfriendReturns {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder = new StringBuilder("");
		while (t-- != 0) {
			String[] s = bufferedReader.readLine().split(" ");
			int n = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]);
			s = bufferedReader.readLine().split(" ");
			Long[] a = new Long[n + m];
			for (int i = 0; i < n; i++) {
				a[i] = Long.parseLong(s[i]);
			}
			s = bufferedReader.readLine().split(" ");
			for (int i = 0; i < m; i++) {
				a[n + i] = Long.parseLong(s[i]);
			}
			mergeSortReverse(a, 0, n - 1, m + n - 1);
			for (int i = 0; i < n+m; i++) {
				stringBuilder.append(a[i] + " ");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);

	}

	public static void mergeSortReverse(Long[] a, int start, int mid, int end) {
		int len1 = mid - start + 1;
		int len2 = end - mid;
		Long[] arr1 = new Long[len1 + 1];
		Long[] arr2 = new Long[len2 + 1];
		arr1[len1] = arr2[len2] = Long.MAX_VALUE;
		for (int i = 0; i < len1; i++) {
			arr1[i] = a[i + start];
		}
		for (int i = 0; i < len2; i++) {
			arr2[i] = a[mid + 1 + i];
		}
		int i = 0, j = 0;
		int k = start;
		while (i < len1 && j < len2 && k <= end) {
			if (arr1[i] > arr2[j]) {
				a[k++] = arr1[i++];
			} else {
				a[k++] = arr2[j++];
			}
		}
		while (i < len1) {
			a[k++] = arr1[i++];
		}
		while (j < len2) {
			a[k++] = arr2[j++];
		}
	}

}
