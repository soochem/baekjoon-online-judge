package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 14890 경사로
// 걸린 시간 2시간ㄴ

public class Sam_14890_2 {
	static int n, l;
	static int[][] map;
	
	static int chkRow() {
		int ans = 0;
		int diff = 0;
		boolean anspos = true;
		boolean[] visit = new boolean[n];
		
		for (int i=0; i<n; i++) {
			anspos = true;
			diff = 0;
			Arrays.fill(visit, false);
			for (int j=1; j<n; j++) {
//				if (!anspos) break;
				diff = map[i][j] - map[i][j-1];
				if (diff == 0)  continue;
				else if (diff == 1) {
					int tmp = map[i][j-1];
					for (int b=1; b<=l; b++) {
						if (j-b >= 0) {
							if (map[i][j-b] != tmp || visit[j-b]) {
								anspos = false;
								break;
							}
							visit[j-b] = true;
						}
						else {
							anspos = false;
							break;
						}
					}
				}
				else if (diff == -1) {
					int tmp = map[i][j];
					for (int b=0; b<l; b++) {
						if (j+b < n) {
							if (map[i][j+b] != tmp || visit[j+b]) {
								anspos = false;
								break;
							}
							visit[j+b] = true;
						}
						else {
							anspos = false;
							break;
						}
					}
					j += l-1;
				}
				else {
					anspos = false;
					break;
				}
			}
			if (anspos) ans++;
		}
		return ans;
	}
	
	static int chkCol() {
		int ans = 0;
		int diff = 0;
		boolean anspos = true;
		boolean[] visit = new boolean[n];
		
		for (int i=0; i<n; i++) {
			anspos = true;
			diff = 0;
			Arrays.fill(visit, false);
			for (int j=1; j<n; j++) {
//				if (!anspos) break;
				diff = map[j][i] - map[j-1][i];
				if (diff == 0) continue;
				else if (diff == 1) {
					int tmp = map[j-1][i];
					for (int b=1; b<=l; b++) {
						if (j-b >= 0) {
							if (map[j-b][i] != tmp || visit[j-b]) {
								anspos = false;
								break;
							}
							visit[j-b] = true;
						}
						else {
							anspos = false;
							break;
						}
					}
				}
				else if (diff == -1) {
					int tmp = map[j][i];
					for (int b=0; b<l; b++) {
						if (j+b < n) {
							if (map[j+b][i] != tmp || visit[j+b]) {
								anspos = false;
								break;
							}
							visit[j+b] = true;
						}
						else {
							anspos = false;
							break;
						}
					}
					j += l-1;
				}
				else {
					anspos = false;
					break;
				}
			}
			if (anspos) ans++;
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
