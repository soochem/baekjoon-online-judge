package samsung_sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


// 문제
// 1. 조합 제대로 못찾음 (범위 제한 때문)
// 2. n은 행 m은 열 수 (그래프를 늘려서 비대칭으로 넣어보자)

//5 7 1
//1 0 1 0 1 1 1
//0 1 0 1 0 0 1 
//1 1 0 0 0 0 1
//0 0 0 1 1 1 1
//1 1 1 1 1 1 0

public class Sam_17135_castleDefence {
	static int n, m, d;
	static int max, enem;
	static int[][] map, visit;
	static int[] sor;  // 궁수 위치 (j: m으로 고정)
	static Eloc[] eloc;  // 적 위치
	
	static class Eloc implements Comparable<Eloc> {
		int i, j;
		Eloc (int i, int j){
			this.i = i;
			this.j = j;
		}
		
		@Override
		public int compareTo(Eloc e) {
			// TODO Auto-generated method stub
			if (this.j > e.j) return 1;  // 오름차순
			else if (this.j == e.j) {
				if (this.i < e.i) return 1; // 내림차순
			}
			return -1;
		}
	}
	
	static void dfs(int num, int depth) {
		if (depth == m) {  // 왜 num == 3??
			if (num != 3) return;  // 궁수 3 선택
			int ans = go();
			if (ans > max) max = ans;
			
//			for(int b:sor) System.out.print(b+" ");
//			System.out.println();
			
			return;
		}
		
		if (3-num <= m-depth)  // 목표치  남은거 (1 > 0 이면 무조건 방문)
			dfs(num, depth+1);  // 선택 x
		
		if (num >= 3) return;  // 얘가 문제
		sor[num] = depth; // 0에 저장
		dfs(num+1, depth+1);
		sor[num] = 0;
	}
	
	static int go() {
		int ans = 0;
		int etmp = enem;
		
		int[][] dist = new int[3][enem];
		boolean[] dead = new boolean[enem];
		
		int[] copy = new int [enem];  // 객체는 복사해도 값이 같이 변함
		for (int i=0; i<enem; i++) copy[i] = eloc[i].i;
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<enem; j++) {
				dist[i][j] = Math.abs(sor[i] - eloc[j].j) 
									+ Math.abs(n - eloc[j].i);  // n이 행
			}
		}
		
		int[] dl = {1000, 1000, 1000};
		int time = 0;
		
		while (etmp > 0) {
			Arrays.fill(dl, 1000);
			// shooting
			for (int i=0; i<3; i++) {
				for (int di=0; di<=d; di++) {
					for (int j=0; j<enem; j++) {
						if (dead[j]) continue;
						if (dist[i][j]-time == di) {
							dl[i] = j;
							break;
						}
					}
					if (dl[i] != 1000) break;
				}
			}
			// 죽은 사람 처리 & 중복처리
			for (int t : dl) {
				if (t == 1000) continue;
				if (dead[t]) continue;
				dead[t] = true;
				ans++;
				etmp--;
			}
			// moving
			for (int j=0; j<enem; j++) {
				if (dead[j]) continue;
				if (++copy[j] == n) {  // n은 행........
					dead[j] = true;
					etmp--;
				}
			}
			time++;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] s;
		
		s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		d = Integer.parseInt(s[2]);
		
		enem = 0;
		max = 0;
		
		map = new int[n][m];
		sor = new int[3];
		eloc = new Eloc[n*m];
		
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(s[j]);
				if (map[i][j] == 1) {
					eloc[enem] = new Eloc(i, j);
					enem++;
				}
			}
		}
		
		Arrays.sort(eloc, 0, enem);
		dfs(0, 0);
		
		bw.write(String.valueOf(max));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
