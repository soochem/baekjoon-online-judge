package samsung_sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 17143 낚시왕

public class Sam_17143 {
	public static int R, C, N, ans, cnt;
	public static class Shark{
		int dir, size, vel;
		Shark(int v, int d, int s){
			this.vel = v;
			this.dir = d;
			this.size = s;
		}
		void init(){
			this.dir = -1;
			this.size = -1;
			this.vel = -1;
		}
		void set(int v, int d, int s){
			this.vel = v;
			this.dir = d;
			this.size = s;
		}
	}
	
	public static void kill (Shark[][] map, int curc) {
		int i, size;
		for (i = 0; i < R; i++) {
			size = map[i][curc].size;
			if (size != -1) {
				ans += size;
				map[i][curc].init();
				cnt--;
				return;
			}
		}
	}
	
	public static void go (Shark[][] map) {
		int i, j, newr, newc;
		int v, d, s, opr, opc;
		
		Shark[][] orig = new Shark[R][C];  // original data
		for (i = 0; i < R; i++) 
			for (j = 0; j < C; j++) 
				orig[i][j] = new Shark(map[i][j].vel, 
								map[i][j].dir, map[i][j].size);
		
		// reset map data
		for (i = 0; i < R; i++) for (j = 0; j < C; j++) map[i][j].init();
		
		for (i = 0; i < R; i++) {
			for (j = 0; j < C; j++) {
				s = orig[i][j].size;
				if (s == -1) continue;
				
				d = orig[i][j].dir;				
				v = orig[i][j].vel;
				
				// newr, newc 찾기
				newr = i; newc = j;
				opr = (R-1)*2;
				opc = (C-1)*2;

				switch(d) {
					case 1:
						newr = i-v%opr;
						d = (newr < 0 && newr > (-1)*R)? 2: d; // 방향이 바뀐다
						newr = (newr < 0)? (newr*-1)%opr: newr;
						newr = (newr < R)? newr: opr-newr;
						break;
					case 2:
						newr = i+v%opr;
						newr = (newr > R)? newr%opr: newr;
						d = (newr/R == 1)? 1: d;
						newr = (newr <= R-1)? newr: opr-newr;
						break;
					case 3:
						newc = j+v%opc;
						newc = (newc > C)? newc%opc: newc;
						d = (newc/C == 1)? 4: d;
						newc = (newc <= C-1)? newc: opc-newc;
						break;
					case 4:
						newc = j-v%opc;
						d = (newc < 0 && newc > (-1)*C)? 3: d; 
						newc = (newc < 0)? (newc*-1)%opc: newc;
						newc = (newc < C)? newc: opc-newc;
						break;
				}

//				System.out.println("? "+d+" "+v);
//				System.out.println(i+" "+j+" -> "+newr+" "+newc);

				int tmp = map[newr][newc].size;  // 추가할 자리에 데이터가 있는가?
				if (tmp < s) {
					map[newr][newc].set(v, d, s); // 새걸로 교체
//					if (newr != i || newc != j) map[i][j].init();
					if (tmp != -1) cnt--;  // 교체했다면, 새로 추가 X
				}
			}
		}
	}
	
	public static void print(Shark[][] map) {
		// 출력 //
		int i, j;
		System.out.println("SIZE");
		for (i = 0; i < R; i++) {
			for (j = 0; j < C; j++) {
				System.out.print(map[i][j].size+" ");
			}System.out.println();
		}System.out.println();
		
		System.out.println("VELOCITY");
		for (i = 0; i < R; i++) {
			for (j = 0; j < C; j++) {
				System.out.print(map[i][j].vel+" ");
			}System.out.println();
		}System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int i, j;
		
		input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		N = Integer.parseInt(input[2]);
		
		Shark[][] map = new Shark[R][C];
		for (i = 0; i < R; i++)
			for (j = 0; j < C; j++) 
				map[i][j] = new Shark(-1, -1, -1);
		
		int r, c;
		for (i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			r = Integer.parseInt(input[0]);
			c = Integer.parseInt(input[1]);
			map[r-1][c-1].set(Integer.parseInt(input[2]), 
					Integer.parseInt(input[3]),
					Integer.parseInt(input[4]));
		}
		
		ans = 0;
		cnt = N;
		for (i = 0; i < C; i++) {
			if (cnt == 0) break;
			kill(map, i);
			go(map);
//			print(map);
		}

		System.out.println(ans);
	}
}
