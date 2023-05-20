import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

class Sushi {

	static int n, m, x;
	static int[] prices;
	static int[][] grades;


	static class Plate {
		int price;
		int averageGrade;
		int id;
		public  Plate(int price, int averageGrade, int id ) {
			this.price = price;
			this.averageGrade = averageGrade;
			this.id = id;
		}
		public double getAverageValue() {
			return averageGrade/price;
	}}

	Sushi(){}
	static Plate[] notaMedie(int[][] grades, int[] prices) {
		Plate[] platouri = new Plate[m];
		for(int i = 0; i < m; i++){
			int sum = 0;
			for(int j = 0; j < n; j++) {
				sum += (grades[j][i]);
				
			}
			platouri[i] = new Plate(prices[i], sum, i);
		}

		return platouri; 
	}
	
	static int task1() {
		// TODO solve task 1
		var plates = notaMedie(grades, prices);
		int[][] dp = new int[m+1][x*n + 1];
		for (int cap = 0; cap <= x*n; ++cap) {
			dp[0][cap] = 0;
		}
		for (int i = 1; i <= m; ++i) {
			for (int cap = 0; cap <= x*n; ++cap) {
				dp[i][cap] = dp[i-1][cap];
				if (cap - prices[i-1] >= 0) {
					int sol_aux = dp[i-1][cap - prices[i-1]] + plates[i-1].averageGrade;
	 
					dp[i][cap] = Math.max(dp[i][cap], sol_aux);
				}
			}
			
		}
		return dp[m][x*n];
	}

	static int task2() {
		var plates = notaMedie(grades, prices);
		int[] res1 = new int[m*2];
		int[] res2 = new int[m*2];
		
		for (int i = 0; i < m; i++) {
			res1[i] = plates[i].averageGrade;
			res1[i+m] = plates[i].averageGrade;
			res2[i] = prices[i];
			res2[i+m] = prices[i];
		}
		int[][] dp = new int[2*m+1][x*n + 1];
		for (int cap = 0; cap <= x*n; ++cap) {
			dp[0][cap] = 0;
		}
		for (int i = 1; i <= 2 * m; ++i) {
			for (int cap = 0; cap <= x*n; ++cap) {
				dp[i][cap] = dp[i-1][cap];
				if (cap - res2[i-1] >= 0) {
					int sol_aux = dp[i-1][cap - res2[i-1]] + res1[i-1];
	 
					dp[i][cap] = Math.max(dp[i][cap], sol_aux);
				}
			}
			
		}
		return dp[2*m][x*n];
	}

	static int task3() {
		var plates = notaMedie(grades, prices);
		int[] res1 = new int[m*2];
		int[] res2 = new int[m*2];
		
		for (int i = 0; i < m; i++) {
			res1[i] = plates[i].averageGrade;
			res1[i+m] = plates[i].averageGrade;
			res2[i] = prices[i];
			res2[i+m] = prices[i];
		}
		int[][][] dp = new int[2*m+1][n*x+1][n + 1];
		for(int j = 1; j <= 2*m; j++){
			for (int i = 1; i <= n*x; i++) {
				for (int cap = 1; cap <= n; cap++) {
					dp[j][i][cap] = dp[j-1][i][cap];
					if (i - res2[j-1] >= 0) {
						int sol_aux = dp[j-1][i-res2[j-1]][cap - 1] + res1[j-1];
		 
						dp[j][i][cap] = Math.max(dp[j-1][i][cap], sol_aux);
					}
				}
				
			}
		}
		
		return dp[2*m][n*x][n];
	}
	
	
	private static void change(int[] aux,  int i, int j){
		int temp = aux[i];
		aux[i] = aux[j];
		aux[j] = temp;
	}
	

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("sushi.in"));

			final int task = sc.nextInt(); // task number

			n = sc.nextInt(); // number of friends
			m = sc.nextInt(); // number of sushi types
			x = sc.nextInt(); // how much each of you is willing to spend

			prices = new int[m]; // prices of each sushi type
			grades = new int[n][m]; // the grades you and your friends gave to each sushi type

			// price of each sushi
			for (int i = 0; i < m; ++i) {
				prices[i] = sc.nextInt();
			}

			// each friends rankings of sushi types
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					grades[i][j] = sc.nextInt();
				}
			}

			int ans;
			switch (task) {
				case 1:
					ans = Sushi.task1();
					break;
				case 2:
					ans = Sushi.task2();
					break;
				case 3:
					ans = Sushi.task3();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("sushi.out");
				fw.write(Integer.toString(ans) + '\n');
				fw.close();

			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
