import java.util.*;
import java.io.*;
 
public class TestClass {
    static int a[];
	static long MOD = (long)1e9 + 7;
	static int N;
    public static void main(String args[] ) throws Exception {
    	InputReader in=new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        
        int n = in.readInt(), q = in.readInt(), i;
        N = n;
       	a = new int[n + 1];
        for(i=1;i<=n;i++) {
        	a[i] = in.readInt();
        }
        Arrays.sort(a);
        long total = pow(n);
        while(q-- > 0) {
        	char c = in.readString().charAt(0);
        	int x = in.readInt();
        	long ans = 0;
        	if(c == '<') {
        		System.out.println(binarySearchWithUpperBound(x));
        		ans = pow(binarySearchWithUpperBound(x));
        	} else if(c == '>') {
        		ans = (total - pow(binarySearchWithUpperBound(x + 1)) + MOD) % MOD;
        	} else {
        		int numElementsLessThanKey = binarySearchWithUpperBound(x);
        		int numElementsGreaterThanKey = binarySearchWithLowerBound(x);
        		System.out.println(numElementsLessThanKey);
        		ans = (pow(N - numElementsGreaterThanKey) - pow(numElementsLessThanKey) + MOD) % MOD;
        	}
        	out.println(ans);
        }
        out.flush();
    }
    static final class InputReader{
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
    public static int binarySearchWithUpperBound(int key) {
    	int low = 1, high = N, mid, ans = 0;
    	while(low <= high) {
    		mid = (low + high) / 2;
    		if(a[mid] < key) {
    			System.out.println(a[mid]);
    			ans = mid;
    			low = mid + 1;
    		} else {
    			high = mid - 1;
    		}
    	}
    	return ans;
    }
    public static int binarySearchWithLowerBound(int key) {
    	int low = 1, high = N, mid, ans = N + 1;
    	while(low <= high) {
    		mid = (low + high) / 2;
    		if(a[mid] > key) {
    			ans = mid;
    			high = mid - 1;
    		} else {
    			low = mid + 1;
    		}
    	}
    	return N - ans + 1;
    }
    public static long pow(int exp) {
    	if(exp == 0) return 1;
    	if(exp % 2 == 0) {
    		long half = pow(exp / 2);
    		return (half * half) % MOD;
    	} else {
    		return (pow(exp - 1) * 2) % MOD;
    	}
    }
}
