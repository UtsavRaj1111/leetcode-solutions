class Solution {
    public boolean sumGame(String num) {
        int fhqCount=0,fhSum=0,shCount=0,shSum=0;

        int[] fvalues=helper(num.substring(0,num.length()/2));
        fhqCount=fvalues[0];fhSum=fvalues[1];

        int[]svalues=helper(num.substring(num.length()/2));
        shCount=svalues[0];shSum=svalues[1];

        if(fhqCount+shCount==0)return fhSum!=shSum;
        int dif=fhSum-shSum;
        int difCount=fhqCount-shCount;
        return difCount == 0?dif != 0:!(dif * difCount < 0 &&2 * Math.abs(dif) == 9 * Math.abs(difCount));
    }
    public int[] helper(String half){
        int qCount=0,sum=0;
        for(char c:half.toCharArray()){
            if(c=='?')qCount++;
            else sum+=Character.getNumericValue(c);
        }
        return new int[]{qCount,sum};
    }
}