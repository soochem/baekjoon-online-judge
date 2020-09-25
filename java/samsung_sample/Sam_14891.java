package samsung_sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sam_14891 {
	static final int N = 4;
	static final int M = 8;
	
	public static void turn(int num, int dir, int[][] map) {
		// num: 톱니 순서 dir: 방향
		int idx, ridx, l, d;
		boolean lmve, rmve;
		
		idx = map[num][6];  // 나의 왼쪽
		ridx = map[num][2];  // 나의 오른쪽
		
		lmve = (num-1 >= 0)? 
				(idx == map[num-1][2])? false: true
				:false;
		rmve = (num+1 < N)? 
				(ridx == map[num+1][6])? false: true
				:false;
		
		// num-th 바퀴 돌리기
		go (num, dir, map);

		l = num; d = dir;
		if (lmve) {
			for (l = num-1; l >= 0; l--) { 
				d *= -1;
//				System.out.println("left "+l);
//				System.out.println("dir : "+d+
//						" left: "+idx+" right: "+map[l][2]);
				if (idx == map[l][2]) break; // 같은 극일 때 멈추기
				
				idx = map[l][6]; // 나의 왼쪽
				go(l, d, map);
			}
		}
		 
		idx = ridx;
		l = num; d = dir;
		if (rmve) {
			for (l = num+1; l < N; l++) { 
				d *= -1;
//				System.out.println("right "+l);
//				System.out.println("dir : "+d+
//						" left: "+idx+" right: "+map[l][6]);
				if (idx == map[l][6]) break;
			
				idx = map[l][2]; // 나의 왼쪽
				go(l, d, map);
			}
		}
	}
	
	public static void go(int num, int dir, int[][] map) {
		int i, tmp;

		switch(dir) {
			case 1:
				tmp = map[num][M-1];
				for (i = M-1; i > 0; i--) {
					map[num][i] = map[num][i-1];
				}
				map[num][0] = tmp;
				break;
			case -1:
				tmp = map[num][0];
				for (i = 0; i < M-1; i++) {
					map[num][i] = map[num][i+1];
				}
				map[num][M-1] = tmp;
				break;
		}
		for (int[] tt: map) {
			for (int t: tt) System.out.print(t);
			System.out.println();
		}System.out.println();
	}
	
	public static int calc(int[][] map) {
		int answer = 0;
		int op = 1;
		for (int[] tmp: map) {
			answer += (tmp[0] == 0)? 0:op;
			op *= 2;
		}
		return answer;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s;
		int i, j, ans, num, dir;
		int[][] map = new int[N][M];
		
		for (i = 0; i < N; i++) {
			s = br.readLine();
			for (j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		int tc = Integer.parseInt(br.readLine());
		for (i = 0; i < tc; i++) {
			String[] tmp = br.readLine().split(" ");
			num = Integer.parseInt(tmp[0])-1;
			dir = Integer.parseInt(tmp[1]);
			turn(num, dir, map);
		}
		
		ans = calc(map);
		System.out.println(ans);
	}
}
