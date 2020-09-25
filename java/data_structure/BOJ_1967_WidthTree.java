package data_structure;

// 트리의 지름 효율성이 구림..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_1967_WidthTree {

    public static int n, MAX;
//    public static int[] parents;
    public static ArrayList<ArrayList<int[]>> children;
//    public static int[][] weights;

    public static void travel(int current, int w_sum, boolean[] visited) {
        visited[current] = true;
        boolean flag = false;

        ArrayList<int[]> tmp = children.get(current);
        for (int[] item : tmp) {
            int c = item[0];
            int w = item[1];
            if (visited[c]) continue;
//            visited[item] = true;
            travel(c, w_sum + w, visited);
            flag = true;
//            visited[item] = false;
        }

        if (!flag) {
            if (MAX < w_sum) {
                MAX = w_sum;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        n = Integer.parseInt(br.readLine());
        children = new ArrayList<>();
        int i;
        for (i=0; i<n+1; i++) {
            children.add(new ArrayList<>());
        }
//        weights = new int[n+1][n+1];

        for (i=0; i<n-1; i++) {
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);

//            int[] node = {y,x};
            children.get(x).add(new int[]{y,w});
            children.get(y).add(new int[]{x,w});  // 무방향
//            parents[y] = x;
//            weights[x][y] = w;
//            weights[y][x] = w;
        }

        // 탐색
        MAX = 0;

        for (i=1; i<=n; i++) {
            travel(i, 0, new boolean[n+1]);
        }

        System.out.println(MAX);
    }

}
