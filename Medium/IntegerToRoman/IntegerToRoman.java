package Medium.IntegerToRoman;

public class IntegerToRoman {

    // Greedy approach: subtract the largest possible value, repeatedly
    public String intToRoman(int num) {
        int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] simbolos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < valores.length; i++) {
            while (num >= valores[i]) {
                result.append(simbolos[i]);
                num = num - valores[i];
            }
        }

        return result.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        IntegerToRoman solution = new IntegerToRoman();

        int[] testCases = {3, 58, 1994, 4, 9, 40, 90, 400, 3999};
        String[] expected = {"III", "LVIII", "MCMXCIV", "IV", "IX", "XL", "XC", "CD", "MMMCMXCIX"};

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            INTEGER TO ROMAN - GREEDY SOLUTION              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        int passed = 0;
        for (int i = 0; i < testCases.length; i++) {
            String result = solution.intToRoman(testCases[i]);
            boolean pass = result.equals(expected[i]);
            if (pass) passed++;

            System.out.println("Test " + (i + 1) + ": " + (pass ? "✅ PASS" : "❌ FAIL"));
            System.out.println("  Input: " + testCases[i]);
            System.out.println("  Expected: " + expected[i]);
            System.out.println("  Got: " + result);
            System.out.println();
        }

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ Total: " + passed + "/" + testCases.length + " test cases passed");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

