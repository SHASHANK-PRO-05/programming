package part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PuchiandLuggage {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder=new StringBuilder("");
		while (t-- != 0) {
			int n = Integer.parseInt(bufferedReader.readLine());
			Long[] arr = new Long[n];
			Long[] sortThis = new Long[n];
			HashMap<Long, Integer> countCheck = new HashMap<>();
			for (int i = 0; i < n; i++) {
				sortThis[i]=arr[i] = Long.parseLong(bufferedReader.readLine());
				countCheck.put(arr[i], 0);
			}
			mergeSortDivideReverse(sortThis, countCheck, 0, arr.length-1);
			for(int i=0;i<n;i++){
				stringBuilder.append(countCheck.get(arr[i])+" ");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);

	}

	public static void mergeSortDivideReverse(Long[] a, HashMap<Long, Integer> countCheck, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSortDivideReverse(a, countCheck, start, mid);
			mergeSortDivideReverse(a, countCheck, mid + 1, end);
			mergeSortReverse(a, countCheck, start, mid, end);
		}
	}

	public static void mergeSortReverse(Long[] a, HashMap<Long, Integer> countCheck, int start, int mid, int end) {
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
		int count = 0;
		while (i < len1 && j < len2 && k <= end) {
			if (arr1[i] < arr2[j]) {
				countCheck.put(arr1[i], countCheck.get(arr1[i]) + count);
				a[k++] = arr1[i++];
			} else {
				count++;
				a[k++] = arr2[j++];
			}
		}
		while (i < len1) {
			countCheck.put(arr1[i], countCheck.get(arr1[i]) + count);
			a[k++] = arr1[i++];
		}
		while (j < len2) {
			a[k++] = arr2[j++];
		}
	}
}
