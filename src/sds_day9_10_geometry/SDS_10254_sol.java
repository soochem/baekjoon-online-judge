package sds_day9_10_geometry;

import java.util.*;
import java.io.*;

public class SDS_10254_sol {
	static class Point implements Comparable<Point> {
        public int x, y;
        @Override
        public int compareTo(Point other) {
            int ret = ccw(P0.x, P0.y, this.x, this.y, other.x, other.y);
            if (0 != ret) return ret;
            else {
                long d1 = dist(P0.x, P0.y, this.x, this.y);
                long d2 = dist(P0.x, P0.y, other.x, other.y);
                return (d1 == d2)? 0 : (d1 < d2)? -1 : 1; 
            }
        }
    }
    static int N;
    static Point P0;
    static Point[] P = new Point[200001];
    static Point[] stk = new Point[200001];
    static int top;
    static int I, J;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        P0 = new Point();
        for (int i = 0; i < 200000; i++) P[i] = new Point();

        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st;
            P0.x = 10000001; P0.y = 10000001;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                P[i].x = Integer.parseInt(st.nextToken());
                P[i].y = Integer.parseInt(st.nextToken());
                if (P0.y > P[i].y || (P0.y == P[i].y && P0.x > P[i].x)) {
                    P0.x = P[i].x; P0.y = P[i].y;
                }
            }
            
            convexHull();
            for (int i=0; i<N; i++) {
            	System.out.print(P[i].x+ " "+P[i].y);
            	System.out.println();
            }
            rotatingCalipers();

            bw.write(stk[I].x + " " + stk[I].y + " " + stk[J].x + " " + stk[J].y + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void convexHull() {
        Arrays.sort(P, 0, N);
        top = -1;
        for (int i = 0; i < N; i++) {
            while (0 < top && 0 <= ccw(stk[top-1].x, stk[top-1].y, stk[top].x, stk[top].y, P[i].x, P[i].y)) top--;
            stk[++top] = P[i];
        }
        stk[top+1] = stk[0];
    }

    private static void rotatingCalipers() {
        long maxDist = 0;
        int i, j = 0;
        for (i = 0; i <= top; i++) {
            while (i == j || 0 > ccw(0, 0, stk[i+1].x-stk[i].x, stk[i+1].y-stk[i].y, stk[j+1].x-stk[j].x, stk[j+1].y-stk[j].y)) {
                System.out.println(ccw(0, 0, stk[i+1].x-stk[i].x, stk[i+1].y-stk[i].y, stk[j+1].x-stk[j].x, stk[j+1].y-stk[j].y));
            	j++; if (top < j) j = 0;
            }
            long d = dist(stk[i].x, stk[i].y, stk[j].x, stk[j].y);
            if (maxDist < d) {
                maxDist = d;
                I = i; J = j;
            }
        }
    }

    private static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
//        long ret = (long)(x2-x1)*(y3-y1)-(long)(x3-x1)*(y2-y1);
        long ret = (long)(x1*y2+x2*y3+x3*y1)-(long)(x2*y1+x3*y2+x1*y3);
//        return (0 == ret)? 0 : (0 > ret)? -1 : 1;
        return (int)ret;
    }

    private static long dist(int x1, int y1, int x2, int y2) {
        return (long)(x2-x1)*(x2-x1) + (long)(y2-y1)*(y2-y1);
    }
}
