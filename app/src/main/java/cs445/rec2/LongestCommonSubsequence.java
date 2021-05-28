package cs445.rec2;

/**
 * LongestCommonSubsequence is a program that will determine the longest string
 * that is a subsequence of two input strings. This program applies a brute
 * force solution technique.
 *
 * @author Charles Hoot
 * @author William C. Garrison III
 * @version 4.1
 */
public class LongestCommonSubsequence {

    public static void main(String args[]) {

        // TODO: Create reference a variable named possibleSubsequences for storing the bag
        // and assign it a value of null

        BagInterface<String> possibleSubsequences = null;


        if (args.length != 2) {
            System.out.println("Type two strings as command-line arguments. Use --args \"first second\" if using gradle.");
            return;
        }

        String first = args[0];
        String second = args[1];

        // TODO: Add code here to create the bag and add the initial string

        possibleSubsequences = new ArrayBag<String>();
        possibleSubsequences.add(first);

        System.out.println("The string bag contains: " + possibleSubsequences);
        String longest = new String("");

        // TODO: Add code here to implement the longest common subsequence
        // algorithm

        while(!possibleSubsequences.isEmpty())
        {
            String test = possibleSubsequences.remove();

            if (longest.length() < test.length())
            {
                if ( isSubsequence(test, second) )
                    longest = test;
                else if (test.length() > longest.length() + 1 )
                {
                    // generate new strings
                    for (int i = 0; i<test.length(); i++)
                    {
                        String next = test.substring(0,i) + test.substring(i+1);

                        possibleSubsequences.add(next);
                    }
                }
            }
        }


        System.out.println("Longest common subsequence:");
        System.out.println(longest);

    }

    /**
     * Determine if one string is a subsequence of another.
     *
     * @param small The potential subsequence
     * @param large The string of which small may be a subsequence
     * @return True of small is a subsequence of large, false otherwise
     */
    public static boolean isSubsequence(String small, String large) {
        // Assume it is a subsequence until proven otherwise
        boolean result = true;

        // Everything before this index in large has been matched, so only look
        // from this index forward
        int nextChar = 0;

        // Consider each index in small
        for (int i = 0; i < small.length(); i++) {
            // Look for small's next character within large, starting at
            // nextChar
            int pos = large.indexOf(small.charAt(i), nextChar);
            if (pos < 0) {
                // If it doesn't exist, small is not a subsequence
                result = false;
                break;
            } else {
                // If it does exist, don't consider the matched character or
                // anything before it in future iterations
                nextChar = pos + 1;
            }
        }
        return result;
    }
}

