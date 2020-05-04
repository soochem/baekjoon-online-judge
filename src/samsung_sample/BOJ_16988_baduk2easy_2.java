// 2를 기준으로 offer/poll 하면 멀리 떨어진 두 단지를 분석하기 어렵
package samsung_sample;

import java.util.*;
import java.io.*;


public class BOJ_16988_baduk2easy_2 {
	static int n, m;
	static int[][] map;
	static class Dol {
		int r, c;
		Dol (int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> start_r = new ArrayList<>();
		ArrayList<Integer> start_c = new ArrayList<>();
		map = new int [n+1][m+1];
		
		// cnt랑 빨간돌 저장
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		for (int i=0; i<n*m; i++) {
			ans.add(new ArrayList<Integer>());
		}
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					start_r.add(i); start_c.add(j);
				}
			}
		}
		
		int[][] pass = new int[n+1][m+1];
		int[][] dir = {{0,1}, {1,0}, {0,-1},{-1,0}};
		int max = -1; int cnt = 0; int red_cnt = 0;
		
		for (int s=0; s<start_r.size(); s++) {
			int curr = start_r.get(s);
			int curc = start_c.get(s);
			
            Queue<Integer> qr = new LinkedList<>();
			Queue<Integer> qc = new LinkedList<>();
			qr.offer(curr); qc.offer(curc);
			
			System.out.println("new : "+curr+" "+curc);
			cnt = 1; red_cnt = 0;  // 필요한 하얀돌 수, 먹을 수 있는 빨간돌 수 
			pass = new int[n+1][m+1];
			pass[curr][curc] = 1;
			
			while (!qr.isEmpty() && !qc.isEmpty()) {
				// 빨간 돌 꺼내기
				curr = qr.poll(); curc = qc.poll();
				
				//pass = new int[n+1][m+1];
				pass[curr][curc] = 1;
				//cnt++;
				//System.out.println("cur "+curr+ " "+curc);
				
				boolean end = true;  // 둘러싸인거 중에 0이 없나??
				
				for (int i=0; i<4; i++) {
					int nr = curr+dir[i][0];
					int nc = curc+dir[i][1];
					
					if (inside(nr, nc)) {
						if (pass[nr][nc] == 0) {
							if (map[nr][nc] == 2) {
								red_cnt++;
								qr.offer(nr); qc.offer(nc);
								pass[nr][nc] = 1;
							}
							if (map[nr][nc] == 0) {
								cnt++;
								qr.offer(nr); qc.offer(nc);
							}
						}
					}
				}
				//if (end) break;  // 0이 하나도 없음
			}
			if (red_cnt > max && cnt <= 2) max = red_cnt;
			
			
			System.out.println("cnt : " +cnt+" red :"+red_cnt);
			print(pass);
		}
		
		if (max == -1) System.out.println(0);
		else System.out.println(max); // -1이면 0이 답
		
		// 2 주위를 탐색하는데
		// 1로 감싼다고 했을 때 빈칸이 2개 이하면 죽일 수 있음 -> 죽은 돌 최대 개수
		
	}
	private static boolean inside(int r, int c) {
		if (r>=0 && c>=0 && r<n && c<m) return true;
		return false;
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
