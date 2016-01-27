import java.util.*;

class TestClass {
    public static void main(String args[] ) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        Set<Long> set = new HashSet<Long>();
        int N = scanner.nextInt();
        long num,sum =0;
        int c=0;
        for (int i = 0; i < N; i++) {
            num = scanner.nextLong();
            if(c==3){
                set.add(sum);
                sum=0;
                c=0;
            }
            sum+=num;
            c++;
        }
        //System.out.println(set);
        System.out.println(set.size());
    }
}
