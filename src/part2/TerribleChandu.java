package part2;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TerribleChandu {
	/*
	 * These variables will be used to get the input from 
	 * the dom judge.
	 * 
	 *  br=is a static buffered reader
	 *  st=is a string tokenizer. it uses spaces to split
	 *  
	 */
	static BufferedReader br;
	static StringTokenizer st;

	/*
	 * Driver program
	 */
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
		int t = nextInt();
		StringBuilder builder = new StringBuilder("");
		while (t-- != 0) {
			builder.append((new StringBuilder(next()).reverse())+"\n");
		}
		out.println(builder);
		out.flush();
		out.close();
	}
	
	/*
	 * build your functions below this
	 */
	

	/*
	 * Functions below this comment are all used in 
	 * reading inputs in a fast manner.
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
