// 나무 재테크
// 나무는 여러 그루일수 있다
package samsung_sample;
import java.util.*;
import java.io.*;

public class Sam_16235_treeFund3 {
	
	public static int n, m, k, cnt;
	//public static ArrayList<ArrayList<ArrayList<Integer>>> map;
	public static int[][] nut;
	public static Queue<Integer> x, y, z;
	public static Queue<Tree> dead;
	
	static class Tree {
		int x, y, age;
		Tree(int x, int y, int age){
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
	static class newSort implements Comparator<Tree> {
		@Override
        public int compare(Tree s1, Tree s2) {
	        if (s1.age < s2.age) return -1;
            if (s1.age == s2.age) return 0;
	        return 1;
	    }
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 땅 크기
		m = Integer.parseInt(st.nextToken()); // 나무 수
		k = Integer.parseInt(st.nextToken()); // k년 후
		
		nut = new int[n+1][n+1];
		int[][] a = new int[n+1][n+1];
		cnt = m;
		ArrayList<Tree> trees;
		dead = new LinkedList<Tree>();
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				nut[i][j] = 5;
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		trees = new ArrayList<Tree>();
		for (int j=0; j<m; j++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.add(new Tree(x-1, y-1, age));  // 나이
		}
			
		while (--k >= 0) {
			//System.out.println(cnt);
			if (cnt == 0) break;
			trees.sort(new newSort());
//			for (int s=0; s<trees.size(); s++) {
//				Tree t = trees.get(s);
//				System.out.println(t.age);
//			}
			
			trees = spring(trees);
			summer();
			trees = fall(trees);
			winter(a);
			//print(nut);
		}
		System.out.println(cnt);
	}
	
	static ArrayList<Tree> spring (ArrayList<Tree> trees) {
		ArrayList<Tree> live = new ArrayList<Tree>();
		
		for (int s=0; s<trees.size(); s++) {
			Tree t = trees.get(s);
			int i = t.x; int j = t.y; int age = t.age;
			
			if (nut[i][j] >= age) {
				nut[i][j] -= age;
				t.age++;
				live.add(t);
			}
			else {
				cnt--;
				dead.add(t);
			}
			//nut[i][j] += a[i][j];
		}
		return live;
	}

	static void summer () { // 죽은 트리
		while(!dead.isEmpty()) {
			Tree t = dead.poll();
			int i = t.x; int j = t.y;
			nut[i][j] += (int) t.age/2;
		}
		return;
	}
	
	static ArrayList<Tree> fall (ArrayList<Tree> trees) {
		int[][] dir = {{-1,0},{0,-1},{-1,-1},{1,0},{0,1},{1,1},{-1,1},{1,-1}};
		for (int s=0; s<trees.size(); s++) {
			Tree t = trees.get(s);
			int i = t.x; int j = t.y; int age = t.age;
			if (age%5 == 0) {
				for (int d=0; d<8; d++) {
					int nexti = i+dir[d][0]; int nextj = j+dir[d][1];
					if (nexti>=0 && nextj>=0 && nexti<n && nextj<n) {
						trees.add(new Tree(nexti, nextj, 1));
						cnt++;
					}
				}
			}
		}
		return trees;
	}
	
	static void winter (int[][] a) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				nut[i][j] += a[i][j];
			}
		}
	}

	static void print(int[][] arr) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
