package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

// 17070 파이프 옮기기1
// 백트레킹
// 벡터에서 시간초과 남

public class Sam_17070_bfs {
	static int n, cnt;
	static int[][] map;  // cnt: 그 자리에 파이프가 올 수 있는 경우의 수
	static int[][] dir = {{0,1}, {1,0}, {1,1}};  // 순차적으로 가능
	
//	static class Pipe{
//		int x1, x2, y1, y2;
//		Pipe(int x1, int y1, int x2, int y2){
//			this.x1 = x1;
//			this.y1 = y1;
//			this.x2 = x2;
//			this.y2 = y2;
//		}
//	}
	
	static boolean inside(int x, int y) {
		if (x >= 0 && y >= 0 && x < n && y < n) return true;
		return false;
	}
	
	static void bfs () {
//		Pipe p;
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		Queue<Integer> di = new LinkedList<>();
		int nx, ny;
		int cx, cy, d;
//		boolean flag; 
		
		cnt = 0;
//		q.add(new Pipe(0,0,0,1));
		qx.add(0);
		qy.add(1);
		di.add(0);
		
		while (!qx.isEmpty()) {
//			p = qx.poll();
			cx = qx.poll();
			cy = qy.poll();
			d = di.poll();
			
			if (cx == n-1 && cy == n-1) {
				// 종료 조건이 있어야, 불필요한 계산x
				cnt++;
				continue;
			}
			
			for (int i=0; i<3; i++) {
//				if (i == 0 && p.y1 == cy) continue;  // 세로
//				if (i == 1 && p.x1 == cx) continue;  // 가로
				if (i == 0 && d == 1 || i == 1 && d == 0) continue;
				
				nx = cx+dir[i][0];
				ny = cy+dir[i][1];
				
				if (!inside(nx, ny) || map[nx][ny] == 1) {
					continue;
				}
				if (i == 2 && (map[cx][cy+1] == 1 || map[cx+1][cy] == 1))
					break; 

//				q.add(new Pipe(cx, cy, nx, ny));
				qx.add(cx);
				qy.add(cy);
				di.add(i);
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		String[] s;
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		bfs();
		
		bw.write(String.valueOf(cnt));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
