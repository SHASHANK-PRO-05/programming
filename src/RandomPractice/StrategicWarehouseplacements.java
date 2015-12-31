package RandomPractice;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StrategicWarehouseplacements {
	/*
	 * These variables will be used to get the input from the dom judge.
	 * 
	 * br=is a static buffered reader st=is a string tokenizer. it uses spaces
	 * to split
	 * 
	 */
	static BufferedReader br;
	static StringTokenizer st;

	/*
	 * Driver program
	 */
	static int n, x;
	static int[][] matrix;
	static int[] nodeCount;
	static int[] statisfyingCount;
	static int[] col;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		n = nextInt();
		
		x = nextInt();
		
		
		matrix = new int[n + 1][n + 1];
		for (int i = 0; i < x; i++) {
			int n1 = nextInt(), n2 = nextInt();
			matrix[n1][n2] = 1;
			matrix[n2][n1] = 1;
		}
		nodeCount = new int[n + 1];
		statisfyingCount = new int[n + 1];
		col = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (matrix[i][j] == 1) {
					nodeCount[i]++;
				}
			}
		}
		int max_nodeCount = 0;
		for (int i = 1; i <= n; i++) {
			statisfyingCount[i] = nodeCount[i] + 1;
			max_nodeCount = Math.max(max_nodeCount, nodeCount[i]);
		}
		for (int i = 0; i <= max_nodeCount; i++) {
			for (int j = 1; j <= n; j++) {
				if (nodeCount[j] == i) {
					tryToRemoveNode(j);
				}
			}
		}
		int count = 0;
		for(int i=1;i<=n;i++){
			if(col[i]==0){
				count++;
			}
		}
		out.println(count);
		out.flush();
		out.close();
	}

	/*
	 * build your functions below this
	 */
	public static void tryToRemoveNode(int i) {
		if (statisfyingCount[i] - 1 == 0) {
			return;
		}
		boolean flag = true;
		for (int j = 1; j <= n; j++) {
			if (matrix[i][j] == 1) {
				flag = flag && (statisfyingCount[j] - 1 != 0);
			}
		}
		if (flag == true) {
			col[i] = 1;
			statisfyingCount[i]--;
			for (int j = 1; j <= n; j++) {
				if (matrix[i][j] == 1) {
					statisfyingCount[j]--;
				}
			}
		}
	}

	/*
	 * Functions below this comment are all used in reading inputs in a fast
	 * manner.
	 * 
	 */
	static String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	static int nextInt() {
		return Integer.parseInt(next());
	}

	static long nextLong() {
		return Long.parseLong(next());
	}

	static double nextDouble() {
		return Double.parseDouble(next());
	}

	static String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

}
