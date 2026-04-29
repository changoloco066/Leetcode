package Medium.LongestPalindrome;
public class LongestPalindrome {
    
    // LeetCode solution
    public String longestPalindrome(String s) {
        int maxLength = 0;
        int start = 0;

        for(int i = 0; i < s.length(); i++){
            int odd = expand(s, i, i);
            int even = expand(s, i, i+1);

            if (odd > maxLength){
                maxLength = odd;
                start = i - (odd - 1) / 2;
            }

            if (even > maxLength){
                maxLength = even;
                start = i - (even - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLength);
    }

    private int expand(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--; 
            right++;
        }
        return right - left - 1;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        LongestPalindrome solution = new LongestPalindrome();
        
        // Test case 1
        String s1 = "babad";
        System.out.println("Input: \"" + s1 + "\" → Output: \"" + solution.longestPalindrome(s1) + "\" (Expected: \"bab\" or \"aba\")");
        
        // Test case 2
        String s2 = "cbbd";
        System.out.println("Input: \"" + s2 + "\" → Output: \"" + solution.longestPalindrome(s2) + "\" (Expected: \"bb\")");
        
        // Test case 3
        String s3 = "a";
        System.out.println("Input: \"" + s3 + "\" → Output: \"" + solution.longestPalindrome(s3) + "\" (Expected: \"a\")");
        
        // Test case 4
        String s4 = "ac";
        System.out.println("Input: \"" + s4 + "\" → Output: \"" + solution.longestPalindrome(s4) + "\" (Expected: \"a\" or \"c\")");
        
        // Test case 5
        String s5 = "racecar";
        System.out.println("Input: \"" + s5 + "\" → Output: \"" + solution.longestPalindrome(s5) + "\" (Expected: \"racecar\")");
        
        // Test case 6
        String s6 = "noon";
        System.out.println("Input: \"" + s6 + "\" → Output: \"" + solution.longestPalindrome(s6) + "\" (Expected: \"noon\")");
    }
}