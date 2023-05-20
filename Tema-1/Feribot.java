import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Collections;

public class Feribot {
    public static void main(final String[] args) throws IOException {
		var scanner = new MyScanner(new FileReader("feribot.in"));

		var N = scanner.nextInt();
		var K = scanner.nextInt();

		var masini = new long[N];
		for (var i = 0; i < N; i += 1) {
			masini[i] = scanner.nextLong();
		}

		try (var printer = new PrintStream("feribot.out")) {	
				printer.println( min_cost(K, masini, N));
		}
	}

    
    static boolean verificare(long cost , long[] v , int K, int N) {
        int feribotNumber = 1;
        long costCurent = 0;
        int i = 0;
        while(i < N) {
            if (costCurent + v[i] > cost) {
                costCurent = v[i];
                feribotNumber += 1;
                
            }
            else {
                costCurent += v[i];
            }
            i += 1;
        }
       
        if(feribotNumber <= K){
            return true;}
        return false;
    }

    static public long min_cost(int K , long[] v, int N) {
        long left = findLargestElement(v, N);
        long right = vectorSum(v, N);
        while (left < right){
            long mid = left + (right - left)/2;
            if(verificare(mid, v, K, N)){
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
    static public long vectorSum(long[] v , int N) {
        long sum = 0;
        for(int i = 0; i < N; i++) {
            sum += v[i];
        }
        return sum;
    }


    public static long findLargestElement(long[] v , int N) {
        long max = 0;  
        for (int i = 1; i < N; i++) {
            if (v[i] > max) {  
                max = v[i];
            }
        }
        return max;
    } 
    private static class MyScanner {
		private BufferedReader br;
		private StringTokenizer st;

		public MyScanner(Reader reader) {
			br = new BufferedReader(reader);
		}

		public String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	} 
    
}
