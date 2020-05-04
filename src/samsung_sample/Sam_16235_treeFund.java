// 나무 재테크
// 나무는 여러 그루일수 있다
package samsung_sample;

import java.util.*;
import java.io.*;

public class Sam_16235_treeFund {
	public static int n, m, k;
	public static int cnt;
	//public static int[][][] map;
	public static ArrayList<ArrayList<ArrayList<Integer>>> map;
	public static int[][] nut;
	
	static class newSort implements Comparator<Integer> {
		@Override
        public int compare(Integer s1, Integer s2) {
	        if (s1 < s2) return -1;
            if (s1 == s2) return 0;
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
		
		// Pair란 무엇인가.. 씁
		//map = new int[n+1][n+1][1001];
		map = new ArrayList<>();
		// 리스트(x) 안에 리스트 (y) 안에 리스트 (나무 나이) 저장
		// 이건 초기화 필요
		for (int i=0; i<n; i++) {
			map.add(new ArrayList<>());
			for (int j=0; j<n; j++) {
				map.get(i).add(new ArrayList<>());
			}
		}
		
		nut = new int[n+1][n+1];
		int[][] a = new int[n+1][n+1];
		cnt = m;
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				nut[i][j] = 5;
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int j=0; j<m; j++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.get(x-1).get(y-1).add(Integer.parseInt(st.nextToken())); // 나이
		}
		
		while (--k >= 0) {

			if (cnt == 0) break;
			spring(a);
			fall();
			//winter(a);
		}
		System.out.println(cnt);
	}
	
	static void spring (int[][] a) {
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				//if (map.get(i).get(j).size() > 1) map.get(i).get(j).sort(new newSort());
				// sort 필요없고 최근 애가 젤 어림
				
				int nut_add = 0; // 미리 추가하면 x
				for (int s=map.get(i).get(j).size()-1; s>=0; s--) {
					int age = map.get(i).get(j).get(s);
					//System.out.print(i+" "+j+"의 "+map.get(i).get(j).get(s)+" nut : "+nut[i][j]+" -> ");
					if (age != 0 && age != 1011) {  // 나무가 있음
						if (nut[i][j] >= age) {
							nut[i][j] -= age;
							map.get(i).get(j).set(s, age+1);
							//map[i][j]++; // 나이 먹음
						}
						else {
							// 처리 중요하다. 0으로 할지 없앨지?
							map.get(i).get(j).set(s, 1011);
							//map[i][j] = 0;
							nut_add += (int) age/2; // 죽음, 여름 / 연산자 주의 +=
							cnt--;
						}
					}
					//System.out.println(map.get(i).get(j).get(s)+" nut : "+nut[i][j]);
				}
				
				nut[i][j] += nut_add+a[i][j];
			}
		}
	}
	
	static void fall () {
		int[][] dir = {{-1,0},{0,-1},{-1,-1},{1,0},{0,1},{1,1},{-1,1},{1,-1}};
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int s=0; s<map.get(i).get(j).size(); s++) {
					int age = map.get(i).get(j).get(s);
					//System.out.println("cur"+i+" "+j);
					if (age%5 == 0 && age != 1011) {
						for (int d=0; d<8; d++) {
							int nexti = i+dir[d][0];
							int nextj = j+dir[d][1];
							if (nexti>=0 && nextj>=0 && nexti<n && nextj<n) {
								//System.out.println(nexti+" "+nextj);
								map.get(nexti).get(nextj).add(1); // 여러 그루도 괜찮아
								cnt++;
							}
						}
					}
					
				}
			}
		}
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
