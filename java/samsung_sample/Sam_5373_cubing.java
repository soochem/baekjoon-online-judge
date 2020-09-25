//큐빙
package samsung_sample;

import java.util.*;
import java.io.*;

public class Sam_5373_cubing {
	public static Idx[][] cube;
	
	public static class Idx {
		public char col, pos;
		public int ord;
		public Idx (char col, char pos, int ord){
			this.col = col;
			this.pos = pos;
			this.ord = ord;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		char[] col = {'w','r','g','b','o','y'};
		char[] pos = {'U','F','L','R','B','D'};
		
		int testcase = Integer.parseInt(br.readLine());

		while (--testcase >= 0) {
			cube = new Idx[6][8];
			for (int c=0; c<6; c++) {
				for (int i=0; i<8; i++) {
					cube[c][i] = new Idx(col[c], pos[c], i);
				}
			}
			//print(cube);
			
			int t = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			while (--t >= 0) {
				String s = st.nextToken();
				char c = s.charAt(0);
				int side = -1;
				if (c == 'U') side = 0;
				if (c == 'F') side = 1;
				if (c == 'L') side = 2;
				if (c == 'R') side = 3;
				if (c == 'B') side = 4;
				if (c == 'D') side = 5;
				rot(side, s.charAt(1));
				rot_inside(side, s.charAt(1));
				//print(cube);
			}
			
			int[] up_side = {0,1,2,7,10,3,6,5,4};
			for (int i=0; i<9; i++) {
				if (i==4) {
					System.out.print('w');
					continue;
				}
				int z = up_side[i];
				System.out.print(cube[0][z].col);
				if (i%3 == 2) System.out.println();
			}
		}
		
	}
	private static void rot (int side, char dir) {
		if (side == 0) { // U
			int[] s = {4, 2, 1, 3};
			int[][] d = {{0,1,2},{0,1,2},{0,1,2},{0,1,2}};  // 왜 012얌? B(4)
			rot_rot (s, d, dir);
		}
		if (side == 1) { // F
			int[] s = {0, 2, 5, 3};
			int[][] d = {{4,5,6},{2,3,4},{0,1,2},{6,7,0}};
			rot_rot (s, d, dir);
		}
		if (side == 2) { // L
			int[] s = {0, 4, 5, 1};
			int[][] d = {{6,7,0},{2,3,4},{6,7,0},{6,7,0}};
			rot_rot (s, d, dir);
		}
		if (side == 3) { // R
			int[] s = {0, 1, 5, 4};
			int[][] d = {{2,3,4},{2,3,4},{2,3,4},{6,7,0}};
			rot_rot (s, d, dir);
		}
		if (side == 4) { // B
			int[] s = {0, 3, 5, 2};
			int[][] d = {{0,1,2},{2,3,4},{4,5,6},{6,7,0}};
			rot_rot (s, d, dir);
		}
		if (side == 5) { // D
			int[] s = {1, 2, 4, 3};
			int[][] d = {{4,5,6},{4,5,6},{4,5,6},{4,5,6}};
			rot_rot (s, d, dir);
		}
	}
	private static void rot_rot (int[] side, int[][] idx, char dir) {
		// 한 면 주변을 둘러싼 네면의 한 줄씩 돌리는 방법
		if (dir == '+') {
			Idx tem1 = cube[side[0]][idx[0][0]];
			Idx tem2 = cube[side[0]][idx[0][1]];
			Idx tem3 = cube[side[0]][idx[0][2]];
			
			for (int i=0; i<3; i++) {
				cube[side[i]][idx[i][0]] = cube[side[i+1]][idx[i+1][0]];
				cube[side[i]][idx[i][1]] = cube[side[i+1]][idx[i+1][1]];
				cube[side[i]][idx[i][2]] = cube[side[i+1]][idx[i+1][2]];
			}
			
			cube[side[3]][idx[3][0]] = tem1;
			cube[side[3]][idx[3][1]] = tem2;
			cube[side[3]][idx[3][2]] = tem3;
		}
		else {
			Idx tem1 = cube[side[3]][idx[3][0]];
			Idx tem2 = cube[side[3]][idx[3][1]];
			Idx tem3 = cube[side[3]][idx[3][2]];
			
			for (int i=3; i>0; i--) {
				cube[side[i]][idx[i][0]] = cube[side[i-1]][idx[i-1][0]];
				cube[side[i]][idx[i][1]] = cube[side[i-1]][idx[i-1][1]];
				cube[side[i]][idx[i][2]] = cube[side[i-1]][idx[i-1][2]];
			}
			
			cube[side[0]][idx[0][0]] = tem1;
			cube[side[0]][idx[0][1]] = tem2;
			cube[side[0]][idx[0][2]] = tem3;
		}

	}
	private static void rot_inside (int side, char dir) {
		// 한면을 돌리는 방법
		if (dir == '+') {
			Idx tem1 = cube[side][0];
			Idx tem2 = cube[side][1];
			Idx tem3 = cube[side][2];
			
			cube[side][0] = cube[side][6];
			cube[side][1] = cube[side][7];
			cube[side][7] = cube[side][5];
			cube[side][6] = cube[side][4];
			cube[side][5] = cube[side][3];
			
			cube[side][2] = tem1;
			cube[side][3] = tem2;
			cube[side][4] = tem3;
		}
		else {
			Idx tem1 = cube[side][0];
			Idx tem2 = cube[side][1];
			Idx tem3 = cube[side][2];
			
			cube[side][1] = cube[side][3];
			cube[side][2] = cube[side][4];
			cube[side][3] = cube[side][5];
			cube[side][4] = cube[side][6];
			cube[side][5] = cube[side][7];
			
			cube[side][6] = tem1;
			cube[side][7] = tem2;
			cube[side][0] = tem3;
		}
	}
//	private static void print (Idx[][] cube) {
//		for (int c=0; c<6; c++) {
//			for (int i=0; i<8; i++) {
//				System.out.print(cube[c][i].pos+""+cube[c][i].ord+"-"+cube[c][i].col+"  ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
