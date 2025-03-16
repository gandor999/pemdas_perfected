import java.util.HashMap;

// Author: Geodor Ruales
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // System.out.println("calculate: " + solution.calculate("1 + 2 * 5 + 3 + 10 / 5"));

        // System.out.println("calculate: " + solution.calculate("(1 + 2) * (5 + (5 + 10)) / 5"));

        System.out.println("calculate: " + solution.calculate("((1 + 2) * (5 + (5 + 10)) / 5)"));

        // System.out.println("calculate: " + solution.calculate("5 + 10"));
    }

    public int calculate(String expression) {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append(expression.replaceAll("\\s", ""));

        String leftNumberStr = "";

        if (strBuilder.charAt(0) == '(') {
            int count = 0;
            int startOfIndexPar = 0;
            int endIndexOfPar = 0;

            for (int i = 0; i < strBuilder.length(); i++) {
                if (strBuilder.charAt(i) == '(') {
                    if (count == 0) {
                        startOfIndexPar = i;
                    }
                    count += 1;
                }

                if (strBuilder.charAt(i) == ')') {
                    count -= 1;

                    if (count == 0) {
                        endIndexOfPar = i;
                        strBuilder.replace(startOfIndexPar, endIndexOfPar + 1, String.valueOf(calculate(strBuilder.substring(startOfIndexPar + 1, endIndexOfPar))));
                        i = 0;
                    }
                }
            }
        }

        // System.out.println("string: " + strBuilder.toString() + " | length: " + strBuilder.length());

        while (Character.isDigit(strBuilder.charAt(0))) {
            leftNumberStr += String.valueOf(strBuilder.charAt(0));
            strBuilder.deleteCharAt(0);

            if (strBuilder.isEmpty()) {
                return Integer.valueOf(leftNumberStr);
            }
        }

        int leftNumber = Integer.valueOf(leftNumberStr);

        char symbol = strBuilder.charAt(0);
        strBuilder.deleteCharAt(0);

        switch (symbol) {
            case '+':
                return leftNumber + calculate(strBuilder.toString());

            case '-':
                return leftNumber - calculate(strBuilder.toString());

            case '*':
                leftNumber *= Integer.valueOf(String.valueOf(strBuilder.charAt(0)));
                strBuilder.deleteCharAt(0);
                strBuilder.insert(0, leftNumber);
                return calculate(strBuilder.toString());

            case '/':
                leftNumber /= Integer.valueOf(String.valueOf(strBuilder.charAt(0)));
                strBuilder.deleteCharAt(0);
                strBuilder.insert(0, leftNumber);
                return calculate(strBuilder.toString());

            default:
                return leftNumber;
        }
    }
}
