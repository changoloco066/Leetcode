public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for(int i = 0; i < n-1; i++ ){
            for(int j = i + 1; j < n; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
    
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();
        int[] result = solution.twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(result[0] + ", " + result[1]);
    }
}