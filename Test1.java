//package com.codility;
//import java.util.*;
//
//// you can also use imports, for example:
//// import java.util.*;
//
//public class Solution {
//
//    public static void main(String [] args) {
//        // you can write to stdout for debugging purposes, e.g.
//        System.out.println("This is a debug message");
//    }
//
//    List<int[][]> generateBalancedMatrixes(int n) {
//        if (n % 2 == 1) return Collections.emptyList();
//        List<int[][]> result = new ArrayList<>();
//        int[][] matrix = new int[n][n];
//        generateBalancedMatrixes0(matrix, 0, 0, n, result);
//        return result;
//    }
//
//    void generateBalancedMatrixes0(int[][] matrix, int i, int j, int n, List<int[][]> result) {
//        if(i >= n || j >= n) return;
//        if(i >= n && j >= n) {
//            int[] vCount = new int[n];
//            int[] hCount = new int[n];
//            for (int ii = 0; ii< n;ii++){
//                for(int jj=0; jj < n; jj++) {
//                    if(matrix[ii][jj] == 1) {
//                        hCount[i]++;
//                        vCount[j]++;
//                    }
//                }
//            }
//            for (int ii = 0; ii< n;ii++){
//                for(int jj=0; jj < n; jj++) {
//                    if(hCount[i] > n/2) return;
//                    if(vCount[i] > n/2) return;
//                }
//            }
//            result.add(matrix);
//        }
//
//        matrix[i][j] = 0;
//        generateBalancedMatrixes0(matrix, i+1, j, n, result);
//        generateBalancedMatrixes0(matrix, i, j+1, n, result);
//        matrix[i][j] = 1;
//        generateBalancedMatrixes0(matrix, i+1, j, n, result);
//        generateBalancedMatrixes0(matrix, i, j+1, n, result);
//    }
//}
