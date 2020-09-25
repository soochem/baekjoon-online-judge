package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 17070 파이프 옮기기1
// 백트레킹
// 벡터에서 시간초과 남

public class Sam_17070_dfs {
	static int n, cnt;  // cnt: 그 자리에 파이프가 올 수 있는 경우의 수
	static int[][] map;  
	static int[][] dir = {{0,1}, {1,0}, {1,1}};  // 순차적으로 가능, 가로, 세로, 대각선
	
	static boolean inside(int x, int y) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		return false;
	}
	
	static void dfs (int cx, int cy, int d) {
		int nx, ny;
			
		if (cx == n-1 && cy == n-1) {
			// 종료 조건이 있어야, 불필요한 계산x
//			return 1;  // (방법 2)
			cnt++;
			return;
		}
		
		// int c = 0;
		for (int i=0; i<3; i++) {
			if (i == 0 && d == 1 || i == 1 && d == 0) continue;
			
			nx = cx + dir[i][0];
			ny = cy + dir[i][1];
			
			if (!inside(nx, ny) || map[nx][ny] == 1) continue;
			if (i == 2 && (map[cx][cy+1] == 1 || map[cx+1][cy] == 1)) break; 
			
			dfs(nx, ny, i);
//			c += dfs(nx, ny, i); // 방법 2
		}
		return;
		// return c; // 방법 2 
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		cnt = 0;
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		String[] s;
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		dfs(0, 1, 0);
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
