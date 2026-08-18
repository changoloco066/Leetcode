package Easy.NumberOf1Bits;
public class NumberOf1Bits {
    
    // Solution 1: String Conversion
    public int hammingWeight1(int n) {
        int count = 0;
        String binary = Integer.toBinaryString(n);
        for(char c : binary.toCharArray()) {
            if(c == '1') {
                count++;
            }
        }
        return count;
    }
    
    // Solution 2: Bit Manipulation
    public int hammingWeight2(int n) {
        int count = 0;
        while(n > 0) {
            if((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        NumberOf1Bits solution = new NumberOf1Bits();
        
        int[] testCases = {11, 128, 2147483645, 1, 0, 3};
        int[] expected = {3, 1, 30, 1, 0, 2};
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         NUMBER OF 1 BITS - HAMMING WEIGHT                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        int passed = 0;
        for(int i = 0; i < testCases.length; i++) {
            int n = testCases[i];
            int result1 = solution.hammingWeight1(n);
            int result2 = solution.hammingWeight2(n);
            
            boolean pass1 = (result1 == expected[i]);
            boolean pass2 = (result2 == expected[i]);
            String status = (pass1 && pass2) ? "✅ PASS" : "❌ FAIL";
            
            if(pass1 && pass2) passed++;
            
            System.out.println("Test " + (i+1) + ": " + status);
            System.out.println("  Input: " + n);
            System.out.println("  Binary: " + Integer.toBinaryString(n));
            System.out.println("  Expected: " + expected[i]);
            System.out.println("  Solution 1 (String): " + result1);
            System.out.println("  Solution 2 (Bit Manipulation): " + result2);
            System.out.println();
        }
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Total: " + passed + "/" + testCases.length + " test cases passed");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}