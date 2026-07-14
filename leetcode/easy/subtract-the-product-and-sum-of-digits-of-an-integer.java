class Solution {
    public int subtractProductAndSum(int n) {
        return helper(n,1,0);
    }
    private int helper(int n, int product, int sum){
        if(n == 0){
            return product - sum;
        }
        int digit = n % 10;
    
        return helper(n / 10, product * digit, sum + digit);
    }  
    }
