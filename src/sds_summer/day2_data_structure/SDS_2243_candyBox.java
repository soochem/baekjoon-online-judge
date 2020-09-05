package sds_summer.day2_data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 올라가거나 내려가거나 하나로 맞추는 게 좋아보임

public class SDS_2243_candyBox {
	static int n, a, b, c;
	static int len;
	static final int MAX = (int) 1e6;
	static int[] tree;
	
	static double log2(int num) {
		return Math.log10(num)/Math.log10(2);
	}
	
	static void make() {
		len = 1 << ((int) Math.ceil(log2(MAX))+1);
		tree = new int[len];
		return;
	}
	
 	static int query(int target, int idx, int left, int right) {
 		// 인풋: 순위 (target)
		// 해당 순위의 idx(캔디 맛) 반환
		
 		// 말단 노드 
 		if (left == right) {
			return left;
		}
		
		int mid = (left+right)/2;

		if (tree[idx*2] >= target) {
			return query(target, idx*2, left, mid);
		}
	
		else {  // 오른쪽을 안볼수도?
			return query(target-tree[idx*2], idx*2+1, mid+1, right);
		}
 	}
	
 	
 	// 1+(len/2)-1 부터 시작해서 len/2 까지 인덱스 매긴 상태에서 마지막부터 올라오는 방법은 왠지
 	// left+right/2 해서 내려가는 방법이랑 인데스가 매치가 안된다.
//	static void update(int idx, int val) {
//		// idx: 맛, val: 달라지는 양
//		idx += len/2-1;
//		tree[idx] += val;
//		System.out.println("updating "+idx);
//		while (idx >= 1) {  // 1포함해야지
//			idx /= 2;
//			tree[idx] += val;
//			System.out.println("updating "+idx);
//		}
//		return;
//	}

	static void update(int idx, int val, int left, int right, int target) {
		// idx: 맛, val: 달라지는 양

		if (left > target || right < target) return;
		
		tree[idx] += val;
		
		if (left != right) {  // 1포함해야지
			int mid = (left+right)/2;
	        update(idx*2, val, left, mid, target);
	        update(idx*2+1, val, mid+1, right, target);
		}
		return;
	}
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		
		n = Integer.parseInt(br.readLine());
		make();
		
		for (int i=0; i<n; i++) {
			String[] s = br.readLine().split(" ");
			a = Integer.parseInt(s[0]);
			if (a == 1) {
				// 순위 b인 애 빼고 출력
				b = Integer.parseInt(s[1]);
				int idx = query(b, 1, 1, MAX);
				bw.write(String.valueOf(idx));
				bw.write("\n");
				update(1, -1, 1, MAX, idx);
			}
			else {
				// c가 양수면 넣고 음수면 빼기
				b = Integer.parseInt(s[1]);
				c = Integer.parseInt(s[2]);
				update(1, c, 1, MAX, b);
			}
		}
		
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
