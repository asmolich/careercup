public class RainTrap {
    private int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) return 0;

        int water = 0;

        //priority q

        return water;
    }

    public static void main(String[] args) {
        int[][] input = {
            {12, 13, 1, 12},
            {13, 4, 13, 12},
            {13, 8, 10, 12},
            {12, 13, 12, 12},
            {13, 13, 13, 13}
        };

        int ret = new RainTrap().trapRainWater(input);

        System.out.println(ret);
    }
}

