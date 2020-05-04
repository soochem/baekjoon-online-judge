// 나무 재테크
// 나무는 여러 그루일수 있다
// 틀린 답 나옴 - ????????/
package samsung_sample;
import java.util.*;
import java.io.*;

public class Sam_16235_treeFund2 {
	
	public static int n, m, k, cnt;
	public static int[][] nut;
	public static Queue<Integer> x, y, z;

	static class Tree {
		int x, y, age;
		Tree(int x, int y, int age){
			this.x = x;
			this.y = y;
			this.age = age;
		}
		static class newSort implements Comparator<Tree> {
			@Override
	        public int compare(Tree s1, Tree s2) {
		        if (s1.age < s2.age) return -1;
	            if (s1.age == s2.age) return 0;
		        return 1;
		    }
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
		Queue<Tree> trees;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				nut[i][j] = 5;
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		trees = new LinkedList<Tree>();
		
		for (int j=0; j<m; j++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees.offer(new Tree(x-1, y-1, age));
			//map.get(x-1).get(y-1).add(Integer.parseInt(st.nextToken())); // 나이
		}
			
		while (--k >= 0) {
			System.out.println(cnt);
			if (cnt == 0) break;
			trees = spring(a, trees);
			trees = fall(trees);
			print(nut);
		}
		System.out.println(cnt);
	}
	
	static Queue<Tree> spring (int[][] a, Queue<Tree> trees) {
		Queue<Tree> live = new LinkedList<Tree>();
		Queue<Tree> dead = new LinkedList<Tree>();
		
		while (!trees.isEmpty()) {
			Tree t = trees.poll();
			int i = t.x; int j = t.y;
			
			int nut_add = 0; // 미리 추가하면 x
			int age = t.age;
			if (nut[i][j] >= age) {
				nut[i][j] -= age;
				t.age++;
				live.offer(t);
			}
			else {
				// 처리 중요하다. 0으로 할지 없앨지?
				nut_add += (int) age/2; // 죽음, 여름 / 연산자 주의 +=
				cnt--;
				dead.offer(t);
			}
			nut[i][j] += nut_add+a[i][j];
		}
		return live;
	}
	
	static Queue<Tree> fall (Queue<Tree> trees) {
		int[][] dir = {{-1,0},{0,-1},{-1,-1},{1,0},{0,1},{1,1},{-1,1},{1,-1}};
		while (!trees.isEmpty()) {
			Tree t = trees.poll();
			int i = t.x; int j = t.y; int age = t.age;
			if (age%5 == 0) {
				for (int d=0; d<8; d++) {
					int nexti = i+dir[d][0];
					int nextj = j+dir[d][1];
					if (nexti>=0 && nextj>=0 && nexti<n && nextj<n) {
						trees.offer(new Tree(nexti, nextj, 1));
						//map.get(nexti).get(nextj).add(1); // 여러 그루도 괜찮아
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
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
