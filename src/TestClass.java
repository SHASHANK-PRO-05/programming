
import java.io.*;
import java.util.*;

public class TestClass {
	public static void main(String[] args) throws java.lang.Exception {
		// BufferedReader in=new BufferedReader(new
		// InputStreamReader(System.in));
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int mod = (int) Math.pow(10, 9) + 7;
		String s = in.readString();
		long[] a = new long[s.length() + 1];
		a[1] = s.charAt(0);
		for (int i = 1; i < s.length(); i++)
			a[i + 1] = (a[i] * 10 + s.charAt(i)) % mod;
		int t = in.readInt();
		for (int t1 = 0; t1 < t; t1++) {
			int l1 = in.readInt();
			int r1 = in.readInt();
			int l2 = in.readInt();
			int r2 = in.readInt();
			long hash1 = (a[r1] - (a[l1 - 1] * powf(10, r1 - l1 + 1, mod)) % mod + mod) % mod;
			long hash2 = (a[r2] - (a[l2 - 1] * powf(10, r2 - l2 + 1, mod)) % mod + mod) % mod;
			if (hash1 == hash2)
				out.println("Yes");
			else
				out.println("No");
		}
		out.flush();
		out.close();
	}

	public static long powf(int a, long b, int mod) {
		if (b == 0)
			return 1;
		if (b % 2 == 0) {
			long x = powf(a, b / 2, mod);
			return (x * x) % mod;
		} else {
			long x = powf(a, (b - 1) / 2, mod);
			return (((x * a) % mod) * x) % mod;
		}
	}
}

class InputReader{
            private final InputStream stream;
            private final byte[] buf=new byte[1024];
            private int curChar;
            private int numChars;
            public InputReader(InputStream stream){this.stream=stream;}
            private int read()throws IOException{
                if(curChar>=numChars){
                    curChar=0;
                    numChars=stream.read(buf);
                    if(numChars<=0)
                        return -1;
                }
                return buf[curChar++];
            }
            public final int readInt()throws IOException{return (int)readLong();}
            public final long readLong()throws IOException{
                int c=read();
                while(isSpaceChar(c)){
                    c=read();
                    if(c==-1) throw new IOException();
                }
                boolean negative=false;
                if(c=='-'){
                    negative=true;
                    c=read();
                }
                long res=0;
                do{
                    if(c<'0'||c>'9')throw new InputMismatchException();
                    res*=10;
                    res+=(c-'0');
                    c=read();
                }while(!isSpaceChar(c));
                return negative?(-res):(res);
            }
            public final int[] readIntBrray(int size)throws IOException{
                int[] arr=new int[size];
                for(int i=0;i<size;i++)arr[i]=readInt();
                return arr;
            }
            public final String readString()throws IOException{
                int c=read();
                while(isSpaceChar(c))c=read();
                StringBuilder res=new StringBuilder();
                do{
                    res.append((char)c);
                    c=read();
                }while(!isSpaceChar(c));
                return res.toString();
            }
            private boolean isSpaceChar(int c){
                return c==' '||c=='\n'||c=='\r'||c=='\t'||c==-1;
            }
        }


