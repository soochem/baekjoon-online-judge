// 2를 기준으로 offer/poll 하면 멀리 떨어진 두 단지를 분석하기 어렵
package samsung_sample;

import java.util.*;
import java.io.*;

public class BOJ_16988_baduk2easy_3 {
	static int n, m, max;
	static int[][] map;
	static int[][] pass;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> start_r = new ArrayList<>();
		ArrayList<Integer> start_c = new ArrayList<>();
		ArrayList<Integer> two_r = new ArrayList<>();
		ArrayList<Integer> two_c = new ArrayList<>();
		map = new int [n+1][m+1];
		
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					start_r.add(i); start_c.add(j);
				}
			}
		}
		
		max = -1;
		for (int s=0; s<start_r.size(); s++) {
			int r = start_r.get(s); int c = start_c.get(s);
			map[r][c] = 1;
			
			for (int z=s+1; z<start_r.size(); z++) {
				int curr = start_r.get(z); int curc = start_c.get(z);
				map[curr][curc] = 1;
				
				//System.out.println(r+" "+c+" "+curr+" "+curc);
				
				// BFS
				int ans = 0;
				pass = new int[n+1][m+1];
				
				for (int i=0; i<n; i++) {
					for (int j=0; j<m; j++) {
						if (map[i][j] != 2) continue;
						if (pass[i][j] != 0) continue;
						ans += bfs(i, j);
						if (ans > max) max = ans;
						//System.out.println("ANS "+ans);
						//print(pass);
					}
				}
				map[start_r.get(z)][start_c.get(z)] = 0;
			}
			map[start_r.get(s)][start_c.get(s)] = 0;
		}

		if (max == -1) System.out.println(0);
		else System.out.println(max);
	}
	private static boolean inside(int r, int c) {
		if (r>=0 && c>=0 && r<n && c<m) return true;
		return false;
	}
	private static int bfs(int curr, int curc) {
		int[][] dir = {{0,1}, {1,0}, {0,-1},{-1,0}};
		Queue<Integer> qr = new LinkedList<>();
		Queue<Integer> qc = new LinkedList<>();
		
		qr.offer(curr); qc.offer(curc);
		pass[curr][curc] = 1;
		
		int red_cnt = 0;
		boolean flag = false;
		
		while (!qr.isEmpty() && !qc.isEmpty()) {
			curr = qr.poll(); curc = qc.poll();
			//System.out.println("cur : "+curr+" "+curc);
			
			if (map[curr][curc] == 2) red_cnt++;
			
			for (int i=0; i<4; i++) {
				int nr = curr+dir[i][0];
				int nc = curc+dir[i][1];
				//System.out.println("next : "+nr+" "+nc);
				
				if (inside(nr, nc)) {
					if (pass[nr][nc] == 0) {
						if (map[nr][nc] == 2) {
							qr.offer(nr); qc.offer(nc);
						}
						if (map[nr][nc] == 0) {
							flag = true;
						}
						else pass[nr][nc] = 1;
						// 0 일 때는 다시 봐줘야하지 않을까ㅏㅇ
					}
				}
			}
			//if (flag) break;
			//print(pass);
		}
		
		if (flag) return 0;
		return red_cnt;
	}
	private static void print(int[][] map) {
		System.out.println("----- map -------");
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
