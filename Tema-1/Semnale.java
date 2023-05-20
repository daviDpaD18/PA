import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Semnale {

	static int sig_type, x, y;
	static final int  mod = 1000000007;

	Semnale(){}

	static int type1() {
		int[][] dp = new int[x + y + 1][2];
		dp[1][0] = dp[1][1] = 1;
		for (int i = 1; i <= x + y; i++) {
            dp[i][0] = ((dp[i-1][0] + dp[i-1][1])) % mod;
            dp[i][1] = (dp[i-1][0]) % mod;
        }
		return ((dp[x+y][0])%mod);
	}

	static int type2() {
		//TODO Compute the number of type 2 signals.
		return 0;
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("semnale.in"));

			sig_type = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();

			int ans;
			switch (sig_type) {
				case 1:
					ans = Semnale.type1();
					break;
				case 2:
					ans = Semnale.type2();
					break;
				default:
					ans = -1;
					System.out.println("wrong task number");
			}

			try {
				FileWriter fw = new FileWriter("semnale.out");
				fw.write(Integer.toString(ans));
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
