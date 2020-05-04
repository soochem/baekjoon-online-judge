package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sam_14890 {
	static int n, l;
	static int[][] map;
	
	static int chkRow() {
		int ans = 0;
		int prev = 0;
		int num = 0;
		boolean neg = true;
		boolean first = true;
		boolean anspos = true;
		
		for (int i=0; i<n; i++) {
			prev = map[i][0];
			num = 0;
			neg = false;
			first = true;
			anspos = true;
			for (int j=0; j<n; j++) {
				if (map[i][j] == prev) num++;
				else if (j == n-1 && neg && !first) {
					if (num < l) anspos = false;
				}
				else {
					if (map[i][j] < prev) {  // 감소
						if (!first) {
							if (!neg) anspos = false;
							if (num < l) anspos = false;
						}
						neg = true;
					}
					else { // 증가
						if (num < l) anspos = false;
						if (!first) {
							if (neg) anspos = false;
//							if (num < l) anspos = false;
						}
//						neg = false;
					}
					num = 1;
					first = false;
				}
				prev = map[i][j];
			}
			if (anspos) {

				System.out.println("행 "+ i);
				ans++;
			}
		}
		return ans;
	}
	static int chkCol() {
		int ans = 0;
		int prev = 0;
		int num = 0;
		boolean neg = true;
		boolean first = true;
		boolean anspos = true;
		
		for (int i=0; i<n; i++) {
			prev = map[0][i];
			num = 0;
			neg = false;
			first = true;
			anspos = true;
			for (int j=0; j<n; j++) {
				if (map[j][i] == prev) num++;
				else if (j == n-1 && neg && !first) {
					if (num < l) anspos = false;
				}
				else {
					if (map[j][i] < prev) {  // 감소
						if (!first) {
							if (!neg) anspos = false;
							if (num < l) anspos = false;
						}
						neg = true;
					}
					else { // 증가
						if (num < l) anspos = false;
						if (!first) {
							if (neg) anspos = false;
						}
//						neg = false;
					}
					num = 1;
					first = false;
				}
				prev = map[j][i];
			}
			if (anspos) {
				System.out.println("열 "+ i);ans++;
			}
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] s;
		
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		l = Integer.parseInt(s[1]);
		
		map = new int [n][n];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

		int ans = 0;
		ans += chkRow();
		ans += chkCol();
		
		sb.append(ans);
		System.out.println(sb.toString());
	}
}
