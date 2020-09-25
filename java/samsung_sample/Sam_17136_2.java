package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 17136 색종이 붙이기

public class Sam_17136_2 {
	static final int max = 1000;
	static int min;
	static int[][] map;
	static int[] cnt;
	static boolean[][] visited;
	
	static boolean inside(int x, int y) {
		if (x >= 0 && y >= 0 && x < 10 && y < 10) return true;
		else return false;
	}
	
	static void dfs(int x, int y, int ans) {
		boolean flag = true;  // 답 없는 경우 뜻함
		
		if (ans > min) return;
		
		for (int j=x; j<10; j++) {  // 줄은 다시 볼 필요x 하지만 열은 다시 봐야한다
			for (int i=0; i<10; i++) {
//				if (j==x && i<=y) continue; // 같은 거 다시 보면 x, 등호!!
				if (visited[j][i]) continue; // 위치 주의
				
				if (map[j][i] == 1) {
					flag = false;
					for (int r=5; r>0; r--) {
						if (cnt[r] == 0) continue; // 종이 다 씀
						if (!inside(j+r-1, i+r-1)) continue;
						
						if (find(j, i, r)) {
							fill(j, i, r, true);
							cnt[r]--;
							dfs(j, i, ans+1); // ans+1? ++ans?
							flag = true; // 위치??
							fill(j, i, r, false);
							cnt[r]++;
							break;  // 무조건 큰게 이득???
						}
					} // 5~1 크기
					
					if (!flag) {  // 하나도 못찾음
						ans = max;
					}
					return;
				}
			}
		}
		
		// 종료조건
		if (ans < min) min = ans;
		return;
	}
	
	static boolean find(int x, int y, int r) {
		// r: 한변 길이
		for (int i=0; i<r; i++) {
			for (int j=0; j<r; j++) {
				if (i==0 && i==j) continue;
				if (visited[x+i][y+j]) return false; // 겹치는거 x
				if (map[x+i][y+j] == 0) return false; 
			}
		}
		return true;
	}
	
	static void fill(int x, int y, int r, boolean val) {
		// r: 한변 길이
		// 방문 표시
		// XOR 연산으로 할 수 있음 (^)
		for (int i=0; i<r; i++) {
			for (int j=0; j<r; j++) {
				visited[x+i][y+j] = val;
			}
		}
		return;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] str;
		
		map = new int[10][10];
		visited = new boolean[10][10];
		cnt = new int[6];
		Arrays.fill(cnt, 5);
		min = max;
		
		for (int j=0; j<10; j++) {
			str = br.readLine().split(" ");
			for (int i=0; i<10; i++) {
				map[j][i] = Integer.parseInt(str[i]);
			}
		}
		
		// find
		dfs(0,0,0);
		
//		for (int j=0; j<10; j++) {
//			for (int i=0; i<10; i++) {
//				System.out.print(visited[j][i]+" ");
//			}
//			System.out.println();
//		}
		
		if (min == max) bw.write(String.valueOf(-1));
		else bw.write(String.valueOf(min));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
