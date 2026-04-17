class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        int n = nums.length;
        map.put(nums[n-1],n-1);
        
        for(int i = n-2; i >= 0; i--){
            int rev = reverse(nums[i]);
            if(map.containsKey(rev)){
                int j = map.get(rev);
                ans = Math.min(ans,j-i);
            }
            map.put(nums[i],i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    public int reverse(int n){
        int t = 0;
        while(n > 0){
            t *= 10;
            t += n%10;
            n /= 10;
        }
        return t;
    }
} 
/*
At max the number can be of only length 10
So the time it will take to reverse a number at worst case would be O(10)
Which gives us the idea about the allowed time complexity which i.e, O(N) as N <= 10^5 
For each number we just need to know the places where it's reverse exists
To get the minimum distance, the mirror of the number can be only on right side
We can optimise the searching via using sorted set
OR 
What we can do is we are only concerned with the first right occurence, 
So why don't we start from the end of the array
Keep a map that stores single index of the number
As the last element would be only seen while checking for the second last element
OR 
To refine we can say, the element can see only on it's right, so it's storing single index
Makes our solution optimal as we will store the recent index of the repeated element
And this also saves us in avoding checking for i == j
*/