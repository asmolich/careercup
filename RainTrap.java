import java.util.*;

class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) return 0;
        
        int[][] copy      = new int[heightMap.length][heightMap[0].length];

        int[][] leftMax   = new int[heightMap.length][heightMap[0].length];
        int[][] rightMax  = new int[heightMap.length][heightMap[0].length];
        int[][] upMax     = new int[heightMap.length][heightMap[0].length];
        int[][] downMax   = new int[heightMap.length][heightMap[0].length];
        int[][] combined  = new int[heightMap.length][heightMap[0].length];
        int[][] combined2 = new int[heightMap.length][heightMap[0].length];

        // left
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 1; j < heightMap[i].length; j++) {
                copy[i][j] = heightMap[i][j];

                if (leftMax[i][j-1] < heightMap[i][j-1]) {
                    leftMax[i][j] = heightMap[i][j-1];
                }
                else {
                    leftMax[i][j] = leftMax[i][j-1];
                }
            }
        }

        // right
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = heightMap[i].length - 2; j >= 0; j--) {
                if (rightMax[i][j+1] < heightMap[i][j+1]) {
                    rightMax[i][j] = heightMap[i][j+1];
                }
                else {
                    rightMax[i][j] = rightMax[i][j+1];
                }
            }
        }
        
        // up
        for (int i = 1; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                if (upMax[i-1][j] < heightMap[i-1][j]) {
                    upMax[i][j] = heightMap[i-1][j];
                }
                else {
                    upMax[i][j] = upMax[i-1][j];
                }
            }
        }

        // down
        for (int i = heightMap.length - 2; i >= 0; i--) {
            for (int j = 0; j < heightMap[i].length; j++) {
                if (downMax[i+1][j] < heightMap[i+1][j]) {
                    downMax[i][j] = heightMap[i+1][j];
                }
                else {
                    downMax[i][j] = downMax[i+1][j];
                }
            }
        }

        // combined
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                combined[i][j] = Math.max(
                    heightMap[i][j],
                    Math.min(
                        Math.min(leftMax[i][j], rightMax[i][j]),
                        Math.min(upMax[i][j], downMax[i][j])
                    )) - heightMap[i][j];
            }
        }

        // combined2
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                combined2[i][j] = combined[i][j] > 0 ? combined[i][j] + heightMap[i][j] : 0;
            }
        }

        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                combined2[i][j] = combined[i][j] > 0 ? combined[i][j] + heightMap[i][j] : 0;
            }
        }

        int water = 0;

        for (int i = 0; i < heightMap.length; i++) {
            System.out.println(Arrays.toString(heightMap[i]));
        }
        System.out.println("-----");
        System.out.println("-----");
        for (int i = 0; i < leftMax.length; i++) {
            System.out.println(Arrays.toString(leftMax[i]));
        }
        System.out.println("-----");
        for (int i = 0; i < rightMax.length; i++) {
            System.out.println(Arrays.toString(rightMax[i]));
        }
        System.out.println("-----");
        for (int i = 0; i < upMax.length; i++) {
            System.out.println(Arrays.toString(upMax[i]));
        }
        System.out.println("-----");
        for (int i = 0; i < downMax.length; i++) {
            System.out.println(Arrays.toString(downMax[i]));
        }
        System.out.println("-----");
        for (int i = 0; i < combined.length; i++) {
            System.out.println(Arrays.toString(combined[i]));
        }
        System.out.println("-----");
        for (int i = 0; i < combined2.length; i++) {
            System.out.println(Arrays.toString(combined2[i]));
        }
        System.out.println("-----");
       
        // calculate
        for (int i = 0; i < heightMap.length; i++) {
            for (int j = 0; j < heightMap[i].length; j++) {
                water += Math.max(
                    heightMap[i][j],
                    Math.min(
                        Math.min(leftMax[i][j], rightMax[i][j]),
                        Math.min(upMax[i][j], downMax[i][j])
                    )) - heightMap[i][j];
            }
        }
        
        return water;
    }
}

public class RainTrap {
    public static void main(String[] args) {
        int[][] input = {
            {12, 13, 1, 12},
            {13, 4, 13, 12},
            {13, 8, 10, 12},
            {12, 13, 12, 12},
            {13, 13, 13, 13}
        };

        int ret = new Solution().trapRainWater(input);
 
        System.out.println(ret);
    }
}

