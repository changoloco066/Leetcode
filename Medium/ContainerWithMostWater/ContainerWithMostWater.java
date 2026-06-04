package Medium.ContainerWithMostWater;

public class ContainerWithMostWater {
    
    // Two Pointers Solution
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while(left < right){
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = width * currentHeight;

            maxArea = Math.max(maxArea, currentArea);

            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();
        
        int[][] testCases = {
            {1, 8, 6, 2, 5, 4, 8, 3, 7},
            {2, 3, 4, 5, 18, 17, 6},
            {1, 1},
            {4, 3, 2, 1, 4},
            {1, 2, 1},
        };
        
        int[] expected = {49, 17, 1, 16, 2};
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      CONTAINER WITH MOST WATER - TWO POINTERS SOLUTION     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        int passed = 0;
        for(int i = 0; i < testCases.length; i++) {
            int[] heights = testCases[i];
            int result = solution.maxArea(heights);
            String status = (result == expected[i]) ? "✅ PASS" : "❌ FAIL";
            
            if(result == expected[i]) passed++;
            
            System.out.println("Test " + (i+1) + ": " + status);
            System.out.print("  Input: [");
            for(int j = 0; j < heights.length; j++) {
                System.out.print(heights[j]);
                if(j < heights.length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("  Expected: " + expected[i] + ", Got: " + result);
            System.out.println();
        }
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Total: " + passed + "/" + testCases.length + " test cases passed");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}