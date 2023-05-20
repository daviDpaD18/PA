/*
 * Acest schelet citește datele de intrare și scrie răspunsul generat de voi,
 * astfel că e suficient să completați cele două metode.
 *
 * Scheletul este doar un punct de plecare, îl puteți modifica oricum doriți.
 *
 * Dacă păstrați scheletul, nu uitați să redenumiți clasa și fișierul.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Nostory {
	public static void main(final String[] args) throws IOException {
		var scanner = new MyScanner(new FileReader("nostory.in"));

		var task = scanner.nextInt();
		var n = scanner.nextInt();
		var moves = task == 2 ? scanner.nextInt() : 0;

		var a = new int[n];
		for (var i = 0; i < n; i += 1) {
			a[i] = scanner.nextInt();
		}

		var b = new int[n];
		for (var i = 0; i < n; i += 1) {
			b[i] = scanner.nextInt();
		}

		try (var printer = new PrintStream("nostory.out")) {
			if (task == 1) {
				printer.println(solveTask1(a, b, n));
			} else {
				printer.println(solveTask2(a, b, moves));
			}
		}
	}
	public static void swapValues(int[] arr, int val1, int val2) {
		int index1 = -1;
		int index2 = -1;
	
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val1) {
				index1 = i;
			}
			if (arr[i] == val2) {
				index2 = i;
			}
			if (index1 != -1 && index2 != -1) {
				break;
			}
		}
	
		change(arr, index1, index2);
	}
	public static void reverseVector(int[] v) {
		int left = 0;
		int right = v.length - 1;
		while (left < right) {
			int temp = v[left];
			v[left] = v[right];
			v[right] = temp;
			left++;
			right--;
		}
	}

	private static long solveTask1(int[] a, int[] b, int n) {
		var aux = new int[2*n];
		for(int i = 0; i < n; i++) {
			aux[i] = a[i];
			aux[n+i] = b[i];
		}
		long scorOpt = scorOptim(aux, n);
		long scorCurent = scor(a, b, n);
		while(true) {
			int j = new Random().nextInt(2*n);
			int i = new Random().nextInt(2*n);
			change(aux, i, j );
			var aux1 = Arrays.copyOf(aux, n);
			var aux2 = Arrays.copyOfRange(aux, n, 2*n);
			long scorNou = scor(aux1, aux2, n);
			if(scorCurent > scorNou) {
				change(aux, i, j);
			}
			else {
				scorCurent = scorNou;
			}
			if(scorCurent == scorOpt){
				return scorCurent;
			}	
		}
		
	}

	private static long solveTask2(int[] a, int[] b, int moves) {
		int n = a.length;
		long totalScore = 0;
		var min = new int[n];
		var max = new int[n];
		for(int i = 0; i < n; i++) {
			if(a[i] >= b[i]) {
				min[i] = b[i];
				max[i] = a[i];
			}
			else {
				min[i] = a[i];
				max[i] = b[i];
			}
		}
		Arrays.sort(min);
		Arrays.sort(max);
		reverseVector(min);
		for(int i = 0; i < moves; i++){
			if(min[i] >= max[i]){
				max[i] = min[i];
			}
		}
		for(int i = 0; i < n; i++) {
			totalScore += max[i];
		}
		return totalScore;
	}
	
	
	private static long scor(int[] a, int[] b, int n ) {
		long score = 0;
		for(int i = 0; i < n; i ++) {
			if(a[i] > b[i]) {
				score += a[i];
			}
			else {
				score += b[i];
			}

		}
		return score;
	}
	
	
	private static void change(int[] aux,  int i, int j){
		int temp = aux[i];
		aux[i] = aux[j];
		aux[j] = temp;
	}
	
	
	private static long scorOptim(int[] v, int n) {
		Arrays.sort(v);
		int i = 0, j = v.length - 1;
    	while (i < j) {
        	int temp = v[i];
        	v[i] = v[j];
        	v[j] = temp;
       	 	i++;
        	j--;
    	}
		long scorOpt = 0;
		for(int k = 0; k < n; k++){
			scorOpt += v[k];
		}
		return scorOpt;
	}
	/*private static void descVector(int[] v) {
		Arrays.sort(v);
		int i = 0, j = v.length - 1;
    	while (i < j) {
        	int temp = v[i];
        	v[i] = v[j];
        	v[j] = temp;
       	 	i++;
        	j--;
    	}
	}*/
	/** 
	 * A class for buffering read operations, inspired from here:
	 * https://pastebin.com/XGUjEyMN.
	 */
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
