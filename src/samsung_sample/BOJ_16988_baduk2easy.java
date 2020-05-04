package samsung_sample;

import java.util.*;
import java.io.*;

public class BOJ_16988_baduk2easy {
	public static int n, m;
	public static int[][] map;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		Queue<Integer> start_r = new LinkedList<>();
		Queue<Integer> start_c = new LinkedList<>();
		map = new int [n+1][m+1];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					start_r.offer(i); start_c.offer(j);
				}
			}
		}
		
		int max = -1; int cnt = 0; int red_cnt = 0;
		int[][] pass = new int[n+1][m+1];
		int[][] dir = {{0,1}, {1,0}, {0,-1},{-1,0}};
		
		while (!start_r.isEmpty() && !start_c.isEmpty()) {
			int curr = start_r.poll();
			int curc = start_c.poll();
			
			//int[][] pass = new int[n+1][n+1];
			
			if (pass[curr][curc] == 2) continue; // »¡°£µ¹¸¸ ÀúÀåÇÏ±â
			else pass[curr][curc] = 2;
			//pass[curr][curc] = 1;
			
			//System.out.println("cur "+curr+" "+curc);
			Queue<Integer> qr = new LinkedList<>();
			Queue<Integer> qc = new LinkedList<>();
			qr.offer(curr); qc.offer(curc);
			
			if (cnt > 2) {
				cnt = 0; red_cnt = 0;  // ÇÊ¿äÇÑ ÇÏ¾áµ¹ ¼ö, ¸ÔÀ» ¼ö ÀÖ´Â »¡°£µ¹ ¼ö 
				pass = new int[n+1][m+1];
			}
			
			while (!qr.isEmpty() && !qc.isEmpty()) {
				//if (cnt > 2) break;
				
				// »¡°£ µ¹ ²¨³»±â
				curr = qr.poll(); curc = qc.poll();
				pass[curr][curc] = 2;
				red_cnt++;
				
				for (int i=0; i<4; i++) {
					int nr = curr+dir[i][0];
					int nc = curc+dir[i][1];
					
					if (inside(nr, nc)) {
						if (pass[nr][nc] == 0) {
							if (map[nr][nc] == 0) {
								//System.out.println("www "+nr+" "+nc);
								cnt++;
								//if (cnt <= 2) qr.offer(nr); qc.offer(nc);
								//qr.offer(nr); qc.offer(nc);  //?
							}
							if (map[nr][nc] == 2) {
								//System.out.println("next red "+nr+" "+nc);
								qr.offer(nr); qc.offer(nc);
								pass[nr][nc] = 2;
							}
							else pass[nr][nc] = 1;
						}
						//pass[nr][nc] = 1;  // »¡°£µ¹Àº ´Ù½Ã ¾ÈºÁµµ µÈ´Ù. ÇÏ¾á/ºóÄ­Àº ´Ù½Ã º¼ ¼ö ÀÖÀ½.
					}
				}
			}
			if (red_cnt > max && cnt <= 2) max = red_cnt;
			
			//System.out.println("red "+ red_cnt +" "+cnt);
			//print(pass);
		}
		
		if (max == -1) System.out.println(0);
		else System.out.println(max); // -1ÀÌ¸é 0ÀÌ ´ä
		
		// 2 ÁÖÀ§¸¦ Å½»öÇÏ´Âµ¥
		// 1·Î °¨½Ñ´Ù°í ÇßÀ» ¶§ ºóÄ­ÀÌ 2°³ ÀÌÇÏ¸é Á×ÀÏ ¼ö ÀÖÀ½ -> Á×Àº µ¹ ÃÖ´ë °³¼ö
		
	}
	private static boolean inside(int r, int c) {
		if (r>=0 && c>=0 && r<n && c<m) return true;
		return false;
	}
	private static void print(int[][] map) {
		System.out.println("map : ");
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
