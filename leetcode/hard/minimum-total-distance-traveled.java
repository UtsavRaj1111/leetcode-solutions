class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        // Step 1: Sort both arrays by position
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> a[0] - b[0]);

        int r = robot.size();
        int f = factory.length;
        long[][] dp = new long[r + 1][f + 1];

        // Step 2: Initialize DP with a large "infinity" value
        for (int i = 0; i <= r; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }
        // Base case: 0 robots always cost 0, regardless of how many factories
        for (int j = 0; j <= f; j++) {
            dp[0][j] = 0;
        }

        // Step 3: Process each factory one by one
        for (int j = 1; j <= f; j++) {
            int pos = factory[j-1][0]; // current factory position
            int limit = factory[j-1][1]; // how many robots it can fix

            // For each possible number of robots (i) we might have repaired so far
            for (int i = 0; i <= r; i++) {
                // Option 1: skip this factory entirely
                dp[i][j] = dp[i][j-1];

                long dist = 0;
                // Option 2: try sending k robots to this factory (k = 1..limit)
                for (int k = 1; k <= limit && i - k >= 0; k++) {
                    // The k-th robot (counting from the end) to assign to this factory
                    // is at index (i - k) in the sorted robot list.
                    dist += Math.abs(robot.get(i - k) - pos);
                    // Best total = best for (i-k) robots using previous factories + current cost
                    dp[i][j] = Math.min(dp[i][j], dp[i - k][j - 1] + dist);
                }
            }
        }

        return dp[r][f];
    }
}