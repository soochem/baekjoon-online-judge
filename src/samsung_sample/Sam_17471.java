package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Sam_17471 {
	static int n, min, tot;
	static final int MAX = (int) 1e8;
	static int[] ppl;
	static int[][] con;
	static boolean[] div, visit;  // div: 조합, visit: 둘다 연결돼 있는지 확인용
	
	static void dfs(int now, boolean flag) {
		int i;
		visit[now] = true;  // 1개가 한 지역구일 때 쳌 안됨
		for (i = 0; i < n; i++) {
			if (flag == div[i] && !visit[i] && con[now][i] == 1) {
				dfs(i, flag);
			}
		}
	}
	
	static void comb(int depth) {
		int i, ppl_num, ans;
		
		if (depth == n) {  // 종료 조건
			Arrays.fill(visit, false);
//			for (boolean b:div) System.out.print(b+" ");
//			System.out.println();
			
			for (i = 0; i < n; i++) {
				if (div[i]) {
//					visit[i] = true;
					dfs(i, true);
					break;
				}
			}
			for (i = 0; i < n; i++) {
				if (!div[i]) {
//					visit[i] = true;
					dfs(i, false);
					break;
				}
			}
			
			// 다 연결되어 있으면 true, true, .., true
//			System.out.print("연결: ");
//			for (boolean b: visit) System.out.print(b+" ");;
//			System.out.println();
			for (boolean b: visit) if (!b) return;
			
			// 답이 될 수도 있다.
			ppl_num = 0;
			for (i = 0; i < n; i++) {
				if (div[i]) {
					ppl_num += ppl[i];
				}
			}
			ans = Math.abs((tot-ppl_num) - ppl_num);
			if (ppl_num == tot || ppl_num == 0) return;
			if (ans < min) min = ans;
			return;
		}
		
		div[depth] = true;
		comb(depth+1);
		div[depth] = false;
		comb(depth+1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] s;
		int i, j;
		
		n = Integer.parseInt(br.readLine());
		min = MAX;
		tot = 0;
		ppl = new int[n];
		con = new int[n][n];
		div = new boolean[n];
		visit = new boolean[n];
		
		s = br.readLine().split(" ");
		for (i = 0; i < n; i++) {
			ppl[i] = Integer.parseInt(s[i]);
			tot += ppl[i];
		}
		
		for (i = 0; i < n; i++) {
			s = br.readLine().split(" ");
			for (j = 1; j < s.length; j++) {
				con[i][Integer.parseInt(s[j])-1] = 1;
			}
		}
		
		comb(0);
		
		if (min == MAX) min = -1;
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
	}
}
