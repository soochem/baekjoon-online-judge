package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 14500 테트로미노

public class Sam_14500_3_simul {
	static int n, m, max;
	static int[][] map;
	
	static boolean inside (int i, int j) {
		if (i >= 0 && i < n && j >= 0 && j < m) return true;
		else return false;
	}
	static int init (int ans, int i, int j) {
		max = (ans > max)? ans:max;
		return map[i][j];
	}
	
	static void simul (int i, int j) {
		int ans = map[i][j];
		
		// 0000
		if (i+3 < n) ans += map[i+1][j] + map[i+2][j] + map[i+3][j];
		ans = init(ans, i, j);
		if (j+3 < m) ans += map[i][j+1] + map[i][j+2] + map[i][j+3];
		ans = init(ans, i, j);
		
		// 000.0
		// 좌우대칭은 i 값 그대로^^,,
		// i 가 위로 올라가면 -1
//		3 3
//		1 1 1
//		0 0 1
//		0 0 0
		if (i-2 >= 0 && j+1 < m) ans += map[i-1][j] + map[i-2][j] + map[i-2][j+1];
		ans = init(ans, i, j);
		if (i-1 >= 0 && j+2 < m) ans += map[i-1][j] + map[i-1][j+1] + map[i-1][j+2];
		ans = init(ans, i, j);
		if (i-2 >= 0 && j+1 < m) ans += map[i][j+1] + map[i-1][j+1] + map[i-2][j+1];
		ans = init(ans, i, j);
		if (i-1 >= 0 && j+2 < m) ans += map[i][j+1] + map[i][j+2] + map[i-1][j+2];
		ans = init(ans, i, j);
		
		if (i-2 >= 0 && j-1 >= 0) ans += map[i-1][j] + map[i-2][j] + map[i-2][j-1];
		ans = init(ans, i, j);
		if (i-1 >= 0 && j-2 >= 0) ans += map[i-1][j] + map[i-1][j-1] + map[i-1][j-2];
		ans = init(ans, i, j);
		if (i-2 >= 0 && j-1 >= 0) ans += map[i][j-1] + map[i-1][j-1] + map[i-2][j-1];
		ans = init(ans, i, j);
		if (i-1 >= 0 && j-2 >= 0) ans += map[i][j-1] + map[i][j-2] + map[i-1][j-2];
		ans = init(ans, i, j);
		
		// 00.00
		if (i+1 < n && j+1 < m) ans += map[i+1][j] + map[i][j+1] + map[i+1][j+1];
		ans = init(ans, i, j);
		
		// 0.00.0
		if (i-2 >= 0 && j+1 < m) ans += map[i-1][j] + map[i-1][j+1] + map[i-2][j+1];
		ans = init(ans, i, j);
		if (j+2 < m && i-1 >= 0) ans += map[i][j+1] + map[i-1][j+1] + map[i-1][j+2];
		ans = init(ans, i, j);
		if (i-2 >= 0 && j-1 >= 0) ans += map[i-1][j] + map[i-1][j-1] + map[i-2][j-1];
		ans = init(ans, i, j);
		if (j-2 >= 0 && i-1 >= 0) ans += map[i][j-1] + map[i-1][j-1] + map[i-1][j-2];
		ans = init(ans, i, j);
		
		// 뽀큐
		if (j-1 >= 0 && i+1 < n && j+1 < m) ans += map[i][j-1] + map[i][j+1] + map[i+1][j];
		ans = init(ans, i, j);
		if (i-1 >= 0 && j+1 < m && j-1 >= 0) ans += map[i][j-1] + map[i][j+1] + map[i-1][j];
		ans = init(ans, i, j);
		if (i-1 >= 0 && i+1 < n && j+1 < m) ans += map[i-1][j] + map[i+1][j] + map[i][j+1];
		ans = init(ans, i, j);
		if (i-1 >= 0 && i+1 < n && j-1 >= 0) ans += map[i-1][j] + map[i+1][j] + map[i][j-1];
		
		max = (ans > max)? ans:max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		
		max = 0;
		map = new int[n][m];
//		visit = new boolean[n][m];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				simul(i, j);
			}
		}
		
		sb.append(max);
		System.out.println(sb.toString());
	}
}
