// 달리기
// 1. merge sort
// 2. seg tree
// 작은 거부터 1로 표시해서 내 위치보다 작은 애 더하기 (--> sum)

package sds_summer.day2_data_structure;

import java.util.*;
import java.io.*;


public class SDS_2517_running {
	static int n, len;
	static Pt[] arr;
	static int[] tree;
	
	static class Pt {
		int idx, val;
		Pt (int i, int v){
			this.idx = i;  // 등수
			this.val = v;  // 실력
		}
	}
	
	static double log2 (int a) {
		return Math.log10(a)/Math.log10(2);
	}
	
	// 세그먼트 트리 함수
	static void newTree () {
		// 트리 만들기
		// 최대 길이는 1 << r+1, 나머지는 0으로 채움
		// r = (int) ceil (log2(n))
		len = (int) Math.ceil(log2(n));
		len = 1 << (len+1);
		tree = new int[len];
		return;
	}
	
//	private static int sum(int l, int r, int i, int L, int R) {
//		if (r < L || R < l)
//			return 0;
//		if (L <= l && r <= R)
//			return tree[i];
//
//		int mid = (l + r) / 2;
//		return sum(l, mid, i * 2, L, R) + sum(mid + 1, r, i * 2 + 1, L, R);
//	}

	static int query (int left, int right) {
		// idx 보다 앞에 있는 작은 수 개수
		// 구간 합을 리턴
		// 구간 합은 left~right 사이의 tree 합 (right 보다 작은 index의 개수라고 할 수 있다)
		int sum = 0;
		left += (len/2-1);
		right += (len/2-1);
		while (left <= right) {
			if (left%2 != 0) {
				sum += tree[left++];
			}
			if (right %2 == 0) {
				sum += tree[right--];
			}
			left /= 2;
			right /= 2;
		}
		
		return sum;
	}
	
	static void update (int idx, int value) {
		// 특정 리프 노드를 업데이트
		// 리프 노드에 부모로 연결된 구간 합도 바꿔야 함
		idx += (len/2-1);
		int diff = value - tree[idx];  // 1-0
		tree[idx] = value;  // 말단
		while (idx >= 1) {
			idx /= 2;  // 이렇게 해야 값 업데이트
			tree[idx] += diff;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 선수 수 <= 5*(1e5)
		n = Integer.parseInt(br.readLine()); 
		len = 0;
		arr = new Pt[n];
		int[] ans = new int[n];

		for (int i=0; i<n; i++) {
			// 속도 향상을 위한 좌표 압축 (배열의 1~500000까지 꽉 찬게 아니라면 꽉 차게 만듦)
			arr[i] = new Pt(i+1, Integer.parseInt(br.readLine()));
		}
				
		//////// 세그먼트 트리 /////////
		// 실력(Pt.val)이 큰 순으로 정렬
		Arrays.sort(arr, new Comparator<Pt>() {
			public int compare(Pt p1, Pt p2) {
				return (p1.val > p2.val)? 1: -1;  // 실력은 모두 다르다 (0 필요 없음)
			}
		});
		
		newTree();
		
//		for (Pt p: arr) {
//			System.out.print((p.idx+(len/2-1))+" "+p.val);
//			System.out.println();
//		}

		for (int i=0; i<n; i++) {
			int idx = arr[i].idx;  // 1~n
			ans[idx-1] = idx - query(1, idx-1);  // 나보다 앞에 있는 애 빼기
			update(idx, 1);
		}
		
		for (int a: ans) bw.write(String.valueOf(a)+"\n");
		
		bw.flush(); bw.close();
	}
}