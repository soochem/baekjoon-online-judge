package samsung_sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// switch - case 배열에서??

public class Sam_12094_2048hard {
	static int n, max;
	static int[][] map;
	static int[] perm;
	
	static void dfs(int depth) {
		if (depth == 5) {
			go();
			return;
		}
		
		for (int i=1; i<=4; i++) {
			perm[depth] = i;
			dfs(depth+1);
		}
	}
	
	static int calc(int[][] tmp) {
		int tmpmax = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (tmpmax < tmp[i][j]) tmpmax = tmp[i][j];
			}
		}
		return tmpmax;
	}
	
	static void go() {		
		int[][] tmp = new int[n][n];
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) tmp[i][j] = map[i][j];
		}
		
		for (int i=0; i<5; i++) {
			// ***** break를 넣지 않으면 뒤에 조건도 시행됨 ***
			// ***** default: break 등
			switch(perm[i]) {
				case 1:
					up(tmp);
					break;
				case 2:
					down(tmp);
					break;
				case 3:
					left(tmp);
					break;
				case 4:
					right(tmp);
					break;
			}
		}
		
		int tmpmax = calc(tmp);
		if (max < tmpmax) max = tmpmax;
		return;
	}
	
	static void up(int[][] tmp) {
		int prev = -1; boolean flag = false;
		for (int j=0; j<n; j++) { //열
			prev = 0;
			flag = false;
			
			for (int i=0; i<n; i++) {
//				if (tmp[i][j] == 0) continue;
//				if (prev == -1) {  // 첫 숫자 발견
//					tmp[0][j] = tmp[i][j];
//					prev = 0;
//					if (i != 0) tmp[i][j] = 0;
//				}
				// 앞에 빈칸 있지만 지금 탐색하는 칸도 수 x
				if (tmp[i][j] == 0 && tmp[prev][j] == 0) continue;
				// 앞에 빈칸 있고 지금 탐색하는 거 수 o
				if (tmp[i][j] != 0 && tmp[prev][j] == 0) {
					tmp[prev][j] = tmp[i][j];
					tmp[i][j] = 0;
				}
				else if (tmp[i][j] == 0) continue;
				else {
					// 앞에 수랑 지금 수랑 같으면 합치기
					if (tmp[i][j] == tmp[prev][j] && !flag) {
						tmp[prev][j] *= 2;
						flag = true;
					}
					else {
						if (tmp[++prev][j] != 0) continue;
						tmp[prev][j] = tmp[i][j];
						flag = false;
					}
					tmp[i][j] = 0;
				}
				for (int[] tm:map) {
					for (int a:tm) {
						System.out.print(a+" ");
					}System.out.println();
				}System.out.println();
				
			}
		}
	}
	
	static void down(int[][] tmp) {
		int prev = -1; boolean flag = false;
		for (int j=0; j<n; j++) { //열
			prev = -1;
			flag = false;
			
			for (int i=n-1; i>=0; i--) {
				if (tmp[i][j] != 0) {
					if (prev == -1) {  // 첫 숫자 발견
						tmp[n-1][j] = tmp[i][j];
						prev = n-1;
						if (i != n-1) tmp[i][j] = 0;
					}
					else {
						if (tmp[i][j] == tmp[prev][j] && !flag) {
							tmp[prev][j] *= 2;
							flag = true;
						}
						else {
							tmp[--prev][j] = tmp[i][j];
							flag = false;
						}
						if (prev != i)  tmp[i][j] = 0;
					}
				}
			}
		}
	}
	
	static void left(int[][] tmp) {
		int prev = -1; boolean flag = false;
		for (int j=0; j<n; j++) { //행
			prev = -1;
			flag = false;
			
			for (int i=0; i<n; i++) {
				if (tmp[j][i] != 0) {
					if (prev == -1) {  // 첫 숫자 발견
						tmp[j][0] = tmp[j][i];
						prev = 0;
						if (i != 0) tmp[j][i] = 0;
					}
					else {
						if (tmp[j][i] == tmp[j][prev] && !flag) {
							tmp[j][prev] *= 2;
							flag = true;
						}
						else {
							tmp[j][++prev] = tmp[j][i];
							flag = false;
						}
						if (prev != i) tmp[j][i] = 0;
					}
				}
			}
		}
	}
	
	static void right(int[][] tmp) {
		int prev = -1; boolean flag = false;
		
		for (int j=0; j<n; j++) { //행
			prev = -1;
			flag = false;
			
			for (int i=n-1; i>=0; i--) {
				if (tmp[j][i] != 0) {
					if (prev == -1) {  // 첫 숫자 발견
						tmp[j][n-1] = tmp[j][i];
						prev = n-1;
						if (i != n-1) tmp[j][i] = 0;
					}
					else {
						if (tmp[j][i] == tmp[j][prev] && !flag) {
							tmp[j][prev] *= 2;
							flag = true;
						}
						else {
							tmp[j][--prev] = tmp[j][i];
							flag = false;
						}
						if (prev != i) tmp[j][i] = 0;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		perm = new int[5];

		String[] s;
		for (int i=0; i<n; i++) {
			s = br.readLine().split(" ");
			for (int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}

//		left(map);
//		right(map);
//		down(map);
		up(map);
		
		max = 0;
//		dfs(0);

		for (int[] tm:map) {
			for (int a:tm) {
				System.out.print(a+" ");
			}System.out.println();
		}System.out.println();
		
		sb.append(max);
		System.out.println(sb.toString());		
	}
}
