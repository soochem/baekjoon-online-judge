package programmers.binary_search;

// 입국 심사

// n : 기다리는 사람 수
// int[] times : 각 심사관이 심사하는 데 걸리는 시간
// 목표 : 모든 사람이 심사를 받는데 최소로 걸리는 시간

//2
//1000000000

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Lv3_immigration {

    public static long binary_search(long start, long end, int n, int[] times) {
        long ans = 0;
        long mid = (start + end) /2;

//        System.out.println(start + " " + end + " " +mid);
        if (start >= end) {
            return mid;
        }

        for (int t : times) {
            ans += mid/t;
        }

        if (ans >= n) {
            mid = binary_search(start, mid, n, times);
        } else {  // n 미만
            mid = binary_search(mid+1, end, n, times);
        }

        return mid;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n, num_jud;
        int[] times;

        n = Integer.parseInt(br.readLine());
        String[] times_input = br.readLine().split(" ");

        times = new int[times_input.length];

        int i = 0;
        long min = 1000000001;

        for (String s : times_input){
            int tmp = Integer.parseInt(s);
//            System.out.println(i + " " + tmp);
            times[i++] = tmp;
            if (tmp < min)
                min = tmp;
        }

//        num_jud = times.length;
//        System.out.println(num_jud);

        long ans = binary_search(min, min*n, n, times);
        System.out.println(ans);

    }
}