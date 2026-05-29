public class PalindromeNumber {
    
    // LeetCode solution
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }  
            left++;
            right--;
        }
        return true;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        PalindromeNumber solution = new PalindromeNumber();
        
        // Test case 1
        int x1 = 121;
        System.out.println("Input: " + x1 + " → Output: " + solution.isPalindrome(x1) + " (Expected: true)");
        
        // Test case 2
        int x2 = -121;
        System.out.println("Input: " + x2 + " → Output: " + solution.isPalindrome(x2) + " (Expected: false)");
        
        // Test case 3
        int x3 = 10;
        System.out.println("Input: " + x3 + " → Output: " + solution.isPalindrome(x3) + " (Expected: false)");
        
        // Test case 4
        int x4 = 0;
        System.out.println("Input: " + x4 + " → Output: " + solution.isPalindrome(x4) + " (Expected: true)");
        
        // Test case 5
        int x5 = 12321;
        System.out.println("Input: " + x5 + " → Output: " + solution.isPalindrome(x5) + " (Expected: true)");
        
        // Test case 6
        int x6 = 1001;
        System.out.println("Input: " + x6 + " → Output: " + solution.isPalindrome(x6) + " (Expected: true)");
    }
}