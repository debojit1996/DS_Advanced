package com.debo.ds;
import java.util.*;

/*
   Sample input example:
   n = 7
   array_input: { 3, 4, 1, 2, 8, 5, 6}
   q = 3
   First query - l = 2, r = 5 (max between index 2 and 5, 0-based indexing)
   O/p - 8
   Second query:  l = 0, r = 3
   O/p - 4
   Third query:  l = 3, r = 6
   O/p - 8
*/
public class SegmentTreeRangeMax {

    public static void main(String[] args) {
        int n,i,q,l,r;
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
        build(ar, 0, n-1, seg, 0);
        q = sc.nextInt();
        while(q > 0) {
            l = sc.nextInt();
            r = sc.nextInt();
            int maxm = query(0, 0, n-1, seg, l, r);
            System.out.println(maxm);
            q--;
        }
    }

    static void build(int[] ar, int low, int high, int[] seg, int index) {
        if (low == high) {
            seg[index] = ar[low];
            return;
        }

        int mid = (low+high)/2;
        build(ar, low, mid, seg, 2*index + 1);
        build(ar, mid+1, high, seg, 2*index + 2);

        seg[index] = Math.max(seg[2*index + 1], seg[2*index + 2]);
    }

    static int query(int index, int low, int high, int[] seg, int l, int r) {
        //low and high completely lies within range [l,r]
        if (low >= l && high <= r) {
            return seg[index];
        }
        // if low and high doesn't lie within range [l,r] at all
        else if (low > r || high < l) {
            return Integer.MIN_VALUE;
        }

        // otherwise, it partially lies within [l,r] and we traverse both ways to find
        // maximum in left and right child trees and finally return the
        // maximum of both
        int mid = (low+high)/2;
        int leftMax = query(2*index + 1, low, mid, seg, l, r);
        int rightMax = query(2*index + 2, mid+1, high, seg, l, r);

        return Math.max(leftMax, rightMax);
    }

}
