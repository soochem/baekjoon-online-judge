package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sam_17144_goodbyeDust {
	static int r, c, t, upper, lower;
	static int[][] map, tmp;
	static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	
	static boolean inside(int x, int y) {
		if (x >= 0 && x < r && y >= 0 && y < c) return true;
		return false;
	}
	
	static int[][] diff() {
		for (int[] tt : tmp) Arrays.fill(tt, 0); 
		int cnt = 0;
		int a, nxtx, nxty;
		
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (map[i][j] == 0) continue;
				if (map[i][j] == -1) {
					tmp[i][j] = -1; continue;
				}
				cnt = 0;
				a = map[i][j]/5;
				// 주위에 칸 몇개?
				for (int d=0; d<4; d++) {
					nxtx = i + dir[d][0];
					nxty = j + dir[d][1];
					if (inside(nxtx, nxty) && map[nxtx][nxty] != -1) {
						cnt++;
						tmp[nxtx][nxty] += a;
					}
				}
				tmp[i][j] += map[i][j] - a*cnt;
			}
		}

		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				map[i][j] = tmp[i][j];
			}
		}
		return tmp;
	}
	
	static void circ() {
		// 왼
		for (int i=upper-1; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		// 위
		for (int i=0; i<c-1; i++) {
			map[0][i] = map[0][i+1];
		}
		// 오른
		for (int i=0; i<upper; i++) {
			map[i][c-1] = map[i+1][c-1];
		}
		// 아래
		for (int i=c-1; i>=2; i--) {
			map[upper][i] = map[upper][i-1];
		}
		map[upper][1] = 0;
		
		// 오른
		for (int i=lower+1; i<r-1; i++) {
			map[i][0] = map[i+1][0];
		}
		// 위
		for (int i=0; i<c-1; i++) {
			map[r-1][i] = map[r-1][i+1];
		}		
		// 왼
		for (int i=r-1; i>lower; i--) {
			map[i][c-1] = map[i-1][c-1];
		}
		// 아래
		for (int i=c-1; i>=2; i--) {
			map[lower][i] = map[lower][i-1];
		}
		map[lower][1] = 0;
	}
	
	static int count() {
		int ans = 0;
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (map[i][j] == 0 || map[i][j] == -1) continue;
				ans += map[i][j];
			}
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		r = Integer.parseInt(s[0]);
		c = Integer.parseInt(s[1]);
		t = Integer.parseInt(s[2]);
		
		map = new int[r][c];
		tmp = new int[r][c];
		upper = -1;
		lower = -1;

		for (int i=0; i<r; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<c; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == -1) {
					if (upper == -1) upper = i;
					else lower = i;
				}
			}
		}
		
		while (--t >= 0) {
			diff();
			circ();
//			for (int[] a:map) {
//				for (int b:a) System.out.print(b+" ");
//				System.out.println();
//			}System.out.println();
		}
		
		int ans = count();
		sb.append(ans);
		System.out.println(sb.toString());
	}
}
