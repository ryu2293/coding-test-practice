import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class SegmentTree {
    long[] tree;

    public SegmentTree(int n) {
        tree = new long[n * 4];
    }

    public long init(long[] arr, int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 1, N);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long answer = 0;
            if (x > y) {
                answer = sum(segmentTree.tree, 1, 1, N, y, x);
            } else {
                answer = sum(segmentTree.tree, 1, 1, N, x, y);
            }
            System.out.println(answer);

            long diff = b - arr[a];
            arr[a] = b;
            update(segmentTree.tree, 1, 1, N, a, diff);
        }
    }

    static long sum(long[] tree, int node, int start, int end, int x, int y) {
        if (x > end || y < start) return 0;

        if (x <= start && y >= end) return tree[node];

        return sum(tree, node * 2, start, (start + end) / 2, x, y)
                + sum(tree, node * 2 + 1, (start + end) / 2 + 1, end, x, y);
    }

    static void update(long[] tree, int node, int start, int end, int a, long diff) {
        if (a < start || a > end) return;

        tree[node] += diff;

        if (start == end) return;

        update(tree, node * 2, start, (start + end) / 2, a, diff);
        update(tree, node * 2 + 1, (start + end) / 2 + 1, end, a, diff);
    }

}