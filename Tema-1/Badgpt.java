import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;



public class Badgpt {
    public static void main(String[] args) { 
        try { 
                var sc = new MyScanner(new FileReader("badgpt.in")); 

                str = sc.next(); 

                int ans = Badgpt.solve(); 

                try { 
                        FileWriter fw = new FileWriter("badgpt.out"); 
                        fw.write(Integer.toString(ans) + '\n'); 
                        fw.close(); 
                } catch (IOException e) { 
                        System.out.println(e.getMessage()); 
                } 
        } catch (FileNotFoundException e) { 
                System.out.println(e.getMessage()); 
        } 
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


