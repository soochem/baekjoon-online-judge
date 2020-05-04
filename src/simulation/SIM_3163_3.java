package simulation;

import java.io.*;
import java.util.*;
                           
public class SIM_3163_3 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test = Integer.parseInt(br.readLine());
		int n; // 개미수
		int l; // 막대 길이
		int k; // 목표 순서
		int[][] arr;
		int[] id;
		
		for (int t=0; t<test; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			int end = n-1;
			int end_min = 0;
			
			arr = new int[n+1][2];
			id = new int[n+1];
			
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (b > 0) {
					arr[i][1] = l-a;
				}
				else {
					arr[i][1] = a;
					end_min++;  // 마이너스 개수
				}
				id[i] = b;
			}
			if (end_min-1 >= 0) end_min--;
			// arraycopy 2차원으로 하면 1차원 주소 참조라서 값 변경할때 같이 바뀜
			
			for (int i=n-1; i>=0; i--) {
				if (id[i] > 0) {
					arr[i][1] = id[end];
					end--;
				}
				if (id[i] < 0) {
					arr[i][1] = id[end_min];
					end_min--;
				}
			}
			
			arr = quickSort(arr, 0, n-1, 0);  // 거리 기준 정렬
			
			int ans = arr[k-1][1];
			if (k-2 >= 0) {
				if (arr[k-2][0] == arr[k-1][0] && arr[k-2][1] > arr[k-1][1]) ans = arr[k-2][1]; 
			}
			if (k < n) {
				if (arr[k][0] == arr[k-1][0] && arr[k][1] < arr[k-1][1]) ans = arr[k][1]; 
			}
			System.out.println(ans);
		}
	}
	
	private static int part (int[][] arr, int start, int end, int idx) {
		int pivot = arr[(start+end)/2][idx];
		
		while (start < end) {
			while (start < end && arr[start][idx] < pivot) start++;
			while (arr[end][idx] > pivot && end > start) end--;

			if (start < end) {
				for (int i=0; i<2; i++) {
					if (i==1) idx = i-idx;
					int tmp = arr[start][idx];
					arr[start][idx] = arr[end][idx];
					arr[end][idx] = tmp;
				}
			}
		}
		return start;
	}
	
	private static int[][] quickSort(int[][] arr, int start, int end, int idx) {
		if (start < end) {
			int pivot = part(arr, start, end, idx);
			quickSort(arr, start, pivot-1, idx);
			quickSort(arr, pivot+1, end, idx);
		}
		return arr;
	}
}
