package part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class ChanduAndHisGirlFriend {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(bufferedReader.readLine());
		StringBuilder stringBuilder = new StringBuilder("");
		while (t-- != 0) {
			int n = Integer.parseInt(bufferedReader.readLine());
			Long[] arr = new Long[n];
			String[] s = bufferedReader.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				arr[i] = Long.parseLong(s[i]);
			}
			mergeSortDivideReverse(arr, 0, arr.length - 1);
			for (int i = 0; i < n; i++) {
				stringBuilder.append(arr[i] + " ");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder);
	}

	public static void mergeSortDivideReverse(Long[] a, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSortDivideReverse(a, start, mid);
			mergeSortDivideReverse(a, mid + 1, end);
			mergeSortReverse(a, start, mid, end);
		}
	}

	public static void mergeSortReverse(Long[] a, int start, int mid, int end) {
		int len1=mid-start+1;
		int len2=end-mid;
		Long[] arr1=new Long[len1+1];
		Long[] arr2=new Long[len2+1];
		arr1[len1]=arr2[len2]=Long.MAX_VALUE;
		for(int i=0;i<len1;i++){
			arr1[i]=a[i+start];
		}
		for(int i=0;i<len2;i++){
			arr2[i]=a[mid+1+i];
		}
		int i=0,j=0;
		int k=start;
		while(i<len1&&j<len2&&k<=end){
			if(arr1[i]>arr2[j]){
				a[k++]=arr1[i++];
			}else{
				a[k++]=arr2[j++];
			}
		}
		while(i<len1){
			a[k++]=arr1[i++];
		}
		while(j<len2){
			a[k++]=arr2[j++];
		}
	}
	// public static void quickSortReverse(Long[] arr, int start, int end) {
	// if (start < end) {
	// int mid = quickSortReverseDivide(arr, start, end);
	// quickSortReverse(arr, start, mid - 1);
	// quickSortReverse(arr, mid + 1, end);
	// }
	// }
	//
	// public static int quickSortReverseDivide(Long[] arr, int start, int end)
	// {
	// int mid = start - 1;
	// int swapTemp = start;
	// while (swapTemp < end) {
	// if (arr[swapTemp] > arr[end]) {
	// mid++;
	// long temp = arr[swapTemp];
	// arr[swapTemp] = arr[mid];
	// arr[mid] = temp;
	// }
	// swapTemp++;
	// }
	// mid++;
	// long temp = arr[end];
	// arr[end] = arr[mid];
	// arr[mid] = temp;
	// return mid;
	// }

}
