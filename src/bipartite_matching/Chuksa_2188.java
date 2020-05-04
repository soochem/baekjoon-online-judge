package bipartite_matching;

import java.util.*;
import java.io.*;

public class Chuksa_2188 {
	public static int n, m;
	public static int[] cow, chuksa;
	public static int[] visit;
	public static ArrayList<ArrayList<Integer>> adj;
	
	static boolean dfs (int a) {
		visit[a] = 1;
		for (int b : adj.get(a)) {
			// 반대편이 매칭되지 않았거나 (B의 초기화 값)
			// 매칭되어 있지만 원래 매칭되어 있던 정점(B[b])을 다른 정점과 매칭(dfs==true)할 수 있으면 성공
			if (chuksa[b] == 0 || visit[chuksa[b]] == 0 && dfs(chuksa[b])) {  // visit[B[b]] == 0
				cow[a] = b;
				chuksa[b] = a;
				return true;
			}
		}
		return false;  // 매칭 실패
	}
	static void print (int[] a) {
		for (int t: a) {
			System.out.print(t+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 초기화
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cow = new int[n+1];
		chuksa = new int[m+1];
		visit = new int[n+1];
		adj = new ArrayList<>();
		for (int i=0; i<n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		
		// 인풋
		for (int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j=0; j<size; j++) {
				adj.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 이분 매칭
		int cnt = 0;
		for (int i=1; i<n+1; i++) {
			visit = new int[n+1];  // 왤까??
			if (dfs(i)) cnt++;
			//print(A); print(B);
		}
		System.out.println(cnt);
	}
	
}
