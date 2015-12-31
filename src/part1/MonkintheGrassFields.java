package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MonkintheGrassFields {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int t = nextInt();
		StringBuilder builder = new StringBuilder("");
		while (t-- != 0) {
			int n = nextInt(), k = nextInt();
			Integer[][] arr = new Integer[n][n];
			Integer[] cSum = new Integer[n];
			Integer[] rSum = new Integer[n];
			PriorityQueue<Integer> cDose = new PriorityQueue<>();
			PriorityQueue<Integer> rDose = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				rSum[i] = cSum[i] = 0;
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					arr[i][j] = nextInt();
					rSum[i] += arr[i][j];
					cSum[j] += arr[i][j];
				}
			}
			for (int i = 0; i < n; i++) {
				cDose.add(cSum[i]);
				rDose.add(rSum[i]);
			}
			Integer[] cResult = letsPollIt(cDose, n, k);
			Integer[] rResult = letsPollIt(rDose, n, k);
			int finalAnswer = Integer.MAX_VALUE;
			for (int i = 0; i <= k; i++) {
				int j = k - i;
				finalAnswer = Math.min(finalAnswer, cResult[i] + rResult[j] + i * j);
			}
			builder.append(finalAnswer + "\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}

	public static Integer[] letsPollIt(PriorityQueue<Integer> dose, int n, int k) {
		Integer[] result = new Integer[k + 1];
		result[0] = 0;
		for (int i = 1; i <= k; i++) {
			int temp = dose.poll();
			result[i] = result[i - 1] + temp;
			dose.add(temp + n);
		}
		return result;
	}

	public static String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public static int nextInt() {
		return Integer.parseInt(next());
	}

	public static long nextLong() {
		return Long.parseLong(next());
	}

	public static double nextDouble() {
		return Double.parseDouble(next());
	}

	public static String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
