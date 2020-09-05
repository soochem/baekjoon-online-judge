package sds_summer.day2_data_structure;

import java.util.*;
import java.io.*;

// 반례
//0 0
//
//-1 -1

public class SDS_6416_tree2 {
	public static class Vert {
		int u, v;
		Vert(int a, int b) {
			this.u = a;
			this.v = b;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		StringTokenizer st;
		
		int testcase = 0;
		ArrayList<Vert> edge = new ArrayList<Vert>();  // 모든 간선
		int[][] adj; // 인접 행렬
		int[] indeg; // 인접 간선 수
		int u, v; u = v = 1;  // u -> v
		int max, tmax; max = tmax = 0;  // 배열 크기 구하려고
		int root = -1;  // 루트
		
		// 트리 탐색용
		boolean flag;  // 사이클 체크
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited;
		
		while (u >= 0 || v >= 0) { 
			// 한줄씩 입력 -> 토큰이 특정 조건 만족할때까지 받음, 아무 정수 주어질 수도 
			st = new StringTokenizer(br.readLine());
			
			while (st.hasMoreTokens()) {  // 엔터에서 에러 날 때 토큰 있을 때까지 받음
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				if (u < 0) break;
				
				else if (u > 0 || v > 0) {
					//temp.u = u; temp.v = v;  // 객체는 포인터로 값 바꿈
					// 연결하기
					edge.add(new Vert(u, v));
					tmax = (u > v)? u:v;
					if (max < tmax) max = tmax;
				}
				
				else {
					testcase++;
					if (edge.size() == 0) {  // 빈 노드도 그래프다
						bw.write("Case "+String.valueOf(testcase)+" is a tree.\n");
						continue;
					}
					
					// 초기화
					flag = false;
					adj = new int [max+1][max+1];
					indeg = new int [max+1];
					Arrays.fill(indeg, -1);
					visited = new boolean [max+1];
					
					for (Vert t : edge) {
						if (indeg[t.u] == -1) indeg[t.u] = 0; // 루트 찾으려고
						if (indeg[t.v] == -1) indeg[t.v] = 1; // 초기화 잘못되는 거 막음
						else indeg[t.v]++;
						adj[t.u][t.v]++;
					}
					
					// 루트는 유일 & 진입 2개 이상 안됨
					root = rootChk(indeg);
					if (root == -1) flag = true;
					else {
						// 루트에서 다른 노드로 가는 유일한 경로 존재, 사이클?
						q.add(root);
						while (!q.isEmpty()) {
							u = q.poll();
							for (int i=0; i<max+1; i++) {
								if (adj[u][i] == 1) {
									if (visited[i]) {
										flag = true; break;
									}
									else {
										q.add(i);
										visited[i] = true;
									}
								}
							}
						}
					}
					
					if (flag) bw.write("Case "+String.valueOf(testcase)+" is not a tree.\n");
					else bw.write("Case "+String.valueOf(testcase)+" is a tree.\n");
					edge.clear();
				}
			}
		}
		
		bw.flush(); bw.close();
	}
	
	private static int rootChk(int[] ind) {  // 루트 반환하는 함수
		int root = -1; int temp = -1;
		
		for (int i=0; i<ind.length; i++) {
			temp = ind[i];
			if (temp == 0) {
				if (root != -1) {  // 루트가 두개
					return -1;
				}
				else root = i;
			}
			else if (temp > 1) {
				return -1;
			}
		}
		return root;
	}
}