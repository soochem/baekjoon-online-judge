package sds_summer.day1_time_complexity;

// 3020 개똥벌레 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
//import java.util.Comparator;

public class SDS_BiS_3020_bug {
	static int n, h;
	static int len;
	static final int MAX = (int) 1e6;
	static int[] up, down;
	
	static int lowerBound(int[] arr, int left, int right, int target) {
		int mid;
		while (left < right) {
			mid = (left+right)/2;
			
			if (arr[mid] < target) {
				left = mid+1;
			}
			else {
				right = mid;
			}
		}
		
		return right;
	}
	
	static int upperBound(int[] arr, int left, int right, int target) {
		// up에서 upper bound 찾기
		int mid;
		while (left < right) {
			mid = (left+right)/2;
			
			if (arr[mid] <= target) {
				left = mid+1;
			}
			else right = mid;
		}
		
		return right;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (System.out));
		String[] s = br.readLine().split(" ");
		
		n = Integer.parseInt(s[0]);
		h = Integer.parseInt(s[1]);
		len = n/2;
		
		up = new int[len];
		down = new int[len];

		for (int i=0; i<n; i++) {
			if (i%2 == 0) down[i/2] = Integer.parseInt(br.readLine());
			else up[i/2] = Integer.parseInt(br.readLine());
		}

		// 오름차순
		Arrays.sort(down);
//		 내림차순
		Arrays.sort(up);
//		Arrays.sort(up, new Comparator<Integer>() {
//			public int compare(Integer i1, Integer i2) {
//				return i1 < i2 ? 1: -1;
//			}
//		});

//		for(int a:down) System.out.print(a+" ");
//		System.out.println();
//		
//		for(int a:up) System.out.print(a+" ");
//		System.out.println();
		
		int l, r; l = 0; r = len-1;
		int left, right; left = 0; right = len-1;
		int num = 0;
		int min = MAX;
		int min_cnt = 0;
		
		for (int i=1; i<=h; i++) { // 구간에 해당값 없어도 찾아야함
//			System.out.println("target "+i+" "+(h-i));

			num = 0;
			l = lowerBound(down, l, r, i);
			if (!(l == len-1 && down[l] < i)) num += len-l;
			while (l<len-1 && down[l+1] == i) l++;
//			System.out.println("lr "+l+" "+r);
//			System.out.println(num);
			if (num > min) continue; // 볼필요업슴

			right = upperBound(up, left, right, h-i);
			if (!(right == len-1 && up[right] <= h-i)) num += len-right;
			
//			System.out.println("lr "+left+" "+right);
//			System.out.println("num2 "+num);
//			System.out.println();
			
			if (min >= num) {
				if (min != num) min_cnt = 1;
				else min_cnt++;
				min = num;
			}
		}
		
		bw.write(String.valueOf(min)+" ");
		bw.write(String.valueOf(min_cnt));
		bw.write("\n");
		bw.flush();
		bw.close();
	}
}
