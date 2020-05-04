package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 17406 배열 돌리기 4
public class Sam_17406_arraySpin {
	static int n, m, k, min;
	static final int MAX = Integer.MAX_VALUE;
	static int[][] map, oper;
	static int[] perm;
	static boolean[] visit;
	
	static void dfs(int depth) {
		if (depth == k) {
			int ans = go();
			if (ans < min) min = ans;
			
//			System.out.println("perm");
//			for (int i:perm)System.out.print(i+" ");
//			System.out.println();
			return;
		}
		
		for (int i=0; i<k; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			perm[depth] = i; // oper 인덱스 넣기 0, 2,... , k
			dfs(depth+1);
			visit[i] = false;
		}
	}
	
	static int go() { // simul
		int[][] tmp = new int[n][m];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
		for (int i=0; i<k; i++) {
			spin(perm[i], tmp);
		}
		int ans = calc(tmp);
		return ans;
	}
	
	static void spin(int op, int[][] tmp) {
		// op: oper의 인덱스
		int r, c, s;
		r = oper[op][0];
		c = oper[op][1];
		s = oper[op][2];
//		System.out.println(r+" "+c+" "+s);
		int now, prev;
		// 범위가 까다로움
		
		for (int d=1; d<=s; d++) {
			now = prev = -1;
			for (int i=c-d; i<=c+d; i++) {
				now = tmp[r-d][i];
				tmp[r-d][i] = prev;
				prev = now;
			}
			for (int i=r-d+1; i<=r+d; i++) {
				now = tmp[i][c+d];
				tmp[i][c+d] = prev;
				prev = now;
			}
			for (int i=c+d-1; i>=c-d; i--) {
				now = tmp[r+d][i];
				tmp[r+d][i] = prev;
				prev = now;
			}
			for (int i=r+d-1; i>r-d; i--) {
				now = tmp[i][c-d];
				tmp[i][c-d] = prev;
				prev = now;
			}
			tmp[r-d][c-d] = prev;
		}
		
//		for (int i=0; i<n; i++) {
//			for (int j=0; j<m; j++) {
//				System.out.print(tmp[i][j]+" ");
//			}System.out.println();
//		}System.out.println();
	}
	
	static int calc(int[][] tmp) {
		int real = MAX;
		int ans = 0;
		for (int i=0; i<n; i++) {
			ans = 0;
			for (int j=0; j<m; j++) {
				ans += tmp[i][j];
			}
			if (ans < real) real = ans;
		}
		return real;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s;
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);
		
		map = new int[n][m];
		oper = new int[k][3];
		perm = new int[k];
		visit = new boolean[k];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		for (int i=0; i<k; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<2; j++) {
				oper[i][j] = Integer.parseInt(s[j])-1;
			}
			oper[i][2] = Integer.parseInt(s[2]);
		}
		
		min = MAX;
		dfs(0);
		
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
	}
}
