import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class SegmentTree {
    long[] tree;

    public SegmentTree() {
        tree = new long[4000000];
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
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree();
        segmentTree.init(arr, 1, 1, N);

        for (int i = 0; i < K + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(segmentTree.tree, 1, 1, N, b, diff);
            }
            else {
                long answer = sum(segmentTree.tree, 1, 1, N, b, (int)c);
                System.out.println(answer);
            }
        }
    }

    static void update(long[] tree, int node, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) {
            return;
        }

        tree[node] += diff;

        if(start == end) return;

        update(tree, node * 2, start, (start + end) / 2, idx, diff);
        update(tree, node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
    }

    static long sum(long[] tree, int node, int start, int end, int left, int right) {
        if(left > end || right < start) {
            return 0;
        }

        if(left <= start && right >= end) {
            return tree[node];
        }

        return sum(tree, node * 2, start, (start + end) / 2, left, right)
                + sum(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }

}