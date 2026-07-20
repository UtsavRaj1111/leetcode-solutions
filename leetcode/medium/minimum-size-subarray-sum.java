class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        
        int j = 0;
        int sum =0;
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];

            while(sum >= target){
                ans = Math.min(ans, i - j + 1);

                sum -= nums[j];

                j++;
            }

        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
        
    }
}

// Note i -> right ans j -> left........