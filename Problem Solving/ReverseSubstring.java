public class ReverseSubstring {
    public static void main(String[] args) {
        System.out.println("Program Start");
        String[] testCases = {"abd(jnb)asdf","abdjnbasdf","dd(df)a(ghhh)"};
        for (String testCase:testCases){
            System.out.println("----------------------------");
            System.out.print(testCase + ": ");
            System.out.println(reverseSubstring(testCase));
            System.out.println("----------------------------");
        }
        System.out.println("Program End");
    }

    private static String reverseSubstring(String input) {
        String output = "";
        String[] inputArray = input.split("");

        for (int i = 0; i<inputArray.length; i++) {
            if (inputArray[i].equals("(")) {
                //start reversing stop at ")"
                int j =0;
                String subString = "";
                output += inputArray[i];

                for (j = i + 1; j<inputArray.length; j++) {
                    if (inputArray[j].equals(")")) {
                        subString += ")";
                        break;
                    } else {
                        subString = inputArray[j] + subString;
                    }
                }

                output += subString;
                i = j;
            } else {
                output += inputArray[i];
            }
        }

        return output;
    }
}