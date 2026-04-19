public class LongestSubstringWithoutRepeatingCharacters {
    
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        for(int right = 0, left = 0; right < s.length(); right++){
            int index = s.indexOf(s.charAt(right), left);
            if (index != right){
                left = index + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();
        
        // Test case 1
        String s1 = "abcabcbb";
        System.out.println("Input: \"" + s1 + "\" → Output: " + solution.lengthOfLongestSubstring(s1) + " (Expected: 3)");
        
        // Test case 2
        String s2 = "bbbbb";
        System.out.println("Input: \"" + s2 + "\" → Output: " + solution.lengthOfLongestSubstring(s2) + " (Expected: 1)");
        
        // Test case 3
        String s3 = "pwwkew";
        System.out.println("Input: \"" + s3 + "\" → Output: " + solution.lengthOfLongestSubstring(s3) + " (Expected: 3)");
        
        // Test case 4
        String s4 = "";
        System.out.println("Input: \"" + s4 + "\" → Output: " + solution.lengthOfLongestSubstring(s4) + " (Expected: 0)");
        
        // Test case 5
        String s5 = "dvdf";
        System.out.println("Input: \"" + s5 + "\" → Output: " + solution.lengthOfLongestSubstring(s5) + " (Expected: 3)");
    }
}