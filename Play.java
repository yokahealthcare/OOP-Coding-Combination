import java.util.ArrayList; 
import java.util.Scanner;
public class Play {
    public static int factorial(int number) {
        int result = 1;
        for(int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String [] args) {
        Scanner inpt = new Scanner(System.in);  // Create a Scanner object
        ArrayList<String> iterables = new ArrayList<>();
        ArrayList<Integer> rs = new ArrayList<>();
        
        int N, R;
        String iterable = "";

        /*
            INPUT FORMAT

            3      ------> Test Case
            4 2    ------> N K
            4 3
            6 3
            
            N : Number of Alphabeth (ABC...)
            K : Number of limit to making of
        */

        // Getting User test case input
        int test_case = inpt.nextInt();
        
        // Getting N & K Input
        for(int lo = 0; lo < test_case; lo++) {
            N = inpt.nextInt();
            R = inpt.nextInt();

            iterable = "";
            // Convert Number to Aplhabet
            for (int i = 1; i <= N; i++) {
                int ascii = 64+i;
                String str = Character.toString((char)ascii);
                iterable += str;
            }

            iterables.add(iterable);
            rs.add(R);
        }
        
        System.out.println();
        for(int lo = 0; lo < test_case; lo++) {
            iterable = iterables.get(lo);
            N = iterable.length();
            R = rs.get(lo);

            int combination = factorial(N) / (factorial(R) * factorial(N-R));
            System.out.println("Case #"+(lo+1)+": "+combination+" combination");

            // result Array List
            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            // Setting 'Pool'
            ArrayList<String> pool = new ArrayList<String>();
            String[] tmp = iterable.split("");
            for(int i = 0; i < tmp.length; i++)
                pool.add(tmp[i]);

            // IF number of asking bigger than AVAILABLE LENGTH
            if(R > tmp.length)
                System.exit(0); // Terminate JVM

            // Setting 'Indices'
            ArrayList<Integer> indices = new ArrayList<>();
            for(int i = 0; i < R; i++)
                indices.add(i);
            // Adding the 1st combinations [0, 1, 2, R..]
            result.add(new ArrayList<Integer>(indices));

            // START HERE
            boolean quit = false;
            while(!quit) {
                int c;
                for(c = R-1; c >= 0; c--) {
                    /*
                        this is for, IF the length of the 'iterator' is SAME AS 'r' (ex. "ABCD" r=4 return "ABCD")
                        also variable 'C' decide all changing (ex. if C = 0, index 0 is changing; if C = 1, index 1 is changing)
                    */
                    if(indices.get(c) != c + (N - R)) 
                        break;

                    if(c == 0) {
                        quit = true;
                        break;
                    }
                }

                // Check the exit status
                if(quit)
                    break;

                indices.set(c, indices.get(c)+1); // Changing the value of array

                // BELOW is function responsibly to reset the back number 
                /*

                EXAMPLE : 
                AB
                AD
                BC -> This one, it reset 'the back number' by one from 'front number' + 1

                */

                for(int j = c+1; j < R; j++)
                    indices.set(j, indices.get(j-1) + 1);

                result.add(new ArrayList<Integer>(indices));
            }

            // Print the result
            for(int y = 0; y < result.size(); y++) {
                for(int x = 0; x < R; x++) {
                    System.out.print(pool.get(result.get(y).get(x)));
                }
                System.out.print("\n");
            }
        }
    }
}