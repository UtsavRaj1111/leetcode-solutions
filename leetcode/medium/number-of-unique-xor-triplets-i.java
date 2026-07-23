class Solution {
    public int uniqueXorTriplets(int[] nums) {
   
        int n = nums.length; 

        if(n <= 2) return n; 
        

        int bitWidth = 31; 
        for(int i = 31; i>= 0; i--) {
            if(((n >> i) & 1) == 1) {
                break; 
            }
            bitWidth--; 
        }

        return (1 << (bitWidth + 1)); 
    }
}