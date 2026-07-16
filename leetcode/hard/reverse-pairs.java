class Solution {
    int count  =0;
    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length-1);
        return count;
        
    }
    private void mergeSort(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int mid = left + (right - left)/2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        countPairs(arr,left,mid, right);
        merge(arr, left, mid, right);
    }
     private void countPairs(int[] arr, int left, int mid, int right) {
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) arr[i] > 2L * arr[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }
    }

    private void merge(int[] arr, int left, int mid, int right){
        int temp[] = new int[right-left+1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }
            else{
                temp[k++] = arr[j++];
            }
        }
        while(i <= mid){
            temp[k++] = arr[i++];

        }
        while(j <= right){
            temp[k++] = arr[j++];
        }
        for(int p = 0; p < temp.length; p++){
            arr[left+p] = temp[p];
        }
    }

}