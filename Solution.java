import java.util.HashMap;

// Author: Geodor Ruales
class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // System.out.println("calculate: " + solution.calculate("1 + 2 * 5 + 3 + 10 / 5"));

        // System.out.println("calculate: " + solution.calculate("(1 + 2) * (5 + (5 + 10)) / 5"));

        // System.out.println("calculate: " + solution.calculate("((1 + 2) * (5 + (5 + 10)) / 5)"));

        System.out.println("calculate: " + solution.calculate("(4 - (1 + 2) * (5 + (5 + 10)) / 5)"));

        // System.out.println("calculate: " + solution.calculate("5 - 10"));
    }

    public int calculate(String expression) {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append(expression.replaceAll("\\s", ""));

        String leftNumberStr = "";

        // for the parenthesis
        if (strBuilder.charAt(0) == '(') {
            int count = 0;
            int startOfIndexPar = 0;
            int endIndexOfPar = 0;

            // find the matching parenthesises parenthesi? parenthesisus?
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
                        // replace the string with the calculated version of the parenthesis already and move on
                        // set i to scan again for this parenthises
                        strBuilder.replace(startOfIndexPar, endIndexOfPar + 1, String.valueOf(calculate(strBuilder.substring(startOfIndexPar + 1, endIndexOfPar))));
                        i = 0;
                    }
                }
            }
        }

        // parse the digits only
        while (Character.isDigit(strBuilder.charAt(0))) {
            leftNumberStr += String.valueOf(strBuilder.charAt(0));
            strBuilder.deleteCharAt(0);

            // if no symbol is encountered then it means that this is the very last number
            if (strBuilder.isEmpty()) {
                return Integer.valueOf(leftNumberStr);
            }
        }

        // let's say that the very last number becomes -8, then just let the left be just 0
        if (leftNumberStr.isEmpty()) {
            leftNumberStr = "0";
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
