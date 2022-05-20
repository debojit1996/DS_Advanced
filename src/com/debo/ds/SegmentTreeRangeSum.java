package com.debo.ds;

import java.util.Scanner;

public class SegmentTreeRangeSum {

    public static void main(String[] args) {
        int n;
        int i;
        int q;
        int l;
        int r;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] ar = new int[n];
        int[] seg = new int[4*n];
        for(i = 0; i < n; i++) {
            ar[i] = sc.nextInt();
            seg[i] = Integer.MIN_VALUE;
        }
        for(i = n; i < 4*n; i++) {
            seg[i] = Integer.MIN_VALUE;
        }
        build(0, seg, ar, 0, n-1);
        q = sc.nextInt();
        while(q > 0) {
            l = sc.nextInt();
            r = sc.nextInt();
            int sum = query(0, 0, n-1, seg, l, r);
            System.out.println(sum);
            q--;
        }
    }

    static void build(int index, int[] seg, int[] ar, int low, int high) {
        if (low == high) {
            seg[index] = ar[low];
            return;
        }

        int mid = low + (high - low)/2;
        build(2*index + 1, seg, ar, low, mid);
        build(2*index + 2, seg, ar, mid+1, high);

        seg[index] = seg[2*index + 1] + seg[2*index + 2];
    }

    static int query(int index, int low, int high, int[] seg, int l, int r) {
        if (low >= l && high <= r) {
            return seg[index];
        }

        else if (low > r || high < l) {
            return 0;
        }

        int mid = low + (high - low)/2;
        int leftSum = query(2*index + 1, low, mid, seg, l , r);
        int rightSum = query(2*index + 2, mid+1, high, seg, l, r);

        return leftSum + rightSum;
    }
}
