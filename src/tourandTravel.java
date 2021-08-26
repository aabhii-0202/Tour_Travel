import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class tourandTravel {
                                    //array to hold the cities. 0th indes is left blank
    static String[] cities = {"nowhere","Bengaluru","Chennai","Goa","Mumbai","Hyderabad","Kolkata","Patna","Delhi","Jaipur","Lukhnow"};
    static int n = cities.length;   //holds the soze of the city
    static float[][] cost;          // the cost matrix to hold the distances values
    public static Scanner sc = new Scanner(System.in);

    private static void printMenu(){    //Dispalys the cities
        for(int i=1;i<n;i++) System.out.println(i+" "+cities[i]);}  //Dispalys the cities

    private static float[][] modifyMatrix(int[] tourcity){  // for traveling sales person problem, it makes a new matrix of selected cities
        int size = tourcity.length;
        float[][] ans = new float[size][size];
        HashSet<Integer> hs = new HashSet<>();
        for (int j : tourcity) hs.add(j);
        int k=0;
        for(int i=0;i<n;i++){
            if(hs.contains(i)&&k<size) {
                int l=0;
                for (int j = 0; j < n; j++) if (hs.contains(j)&&l<size) ans[k][l++]=cost[i][j];
                k++;
            }
        }
        return ans;
    }   // for traveling sales person problem, it makes a new matrix of selected cities

    private static void prepareFile(int a) throws Exception {   // get input form the text file and stores in the matrix
        cost = new float[n][n];
        FileReader fr ;
        if(a==1) fr = new FileReader("C:\\Users\\abhis\\Desktop\\Abhi\\Prog\\Java Project\\Tour & Travel\\cost_matrix.txt");
        else fr = new FileReader("C:\\Users\\abhis\\Desktop\\Abhi\\Prog\\Java Project\\Tour & Travel\\travelling.txt");

        StringBuilder s1=new StringBuilder(); // storing everything in a string builder
        int i;
        while ((i= fr.read())!=-1) s1.append((char)i);
        StringTokenizer st =new StringTokenizer(s1.toString()," ");
        for (i = 0;  i<n ; i++)
            for (int j = 0; j <n; j++)
                cost[i][j]=Float.parseFloat(st.nextToken());
    } // get input form the text file and stores in the matrix

    private static String[] newNames(int[] tourcity){ // for traveling sales person problem, it makes a new string array of selected cities
        String[] ans =new String[tourcity.length];
        Arrays.sort(tourcity);
        for(int i=0;i< tourcity.length;i++) ans[i]=cities[tourcity[i]];
        System.out.println(Arrays.toString(ans));
        return ans;
    }       // for traveling sales person problem, it makes a new string array of selected cities

    public static void main(String[] args)throws Exception {


        while(true) {  // keeoing everything in a while loop
            System.out.println("Welcome to XYZ tour and travels. We have following list of cities :");
            printMenu(); //prints the menu
            System.out.println("Please enter any one choice");
            System.out.println("1.Travel from one place to another.");
            System.out.println("2.Get a full tour planned with minimum trvelling.");
            int choice = sc.nextInt();
            prepareFile(choice); //prepares the file
            if (choice == 1) {
                System.out.println("So you have opted for travelling from one place to another.");
                printMenu();
                System.out.println("Please enter your source");
                int source = sc.nextInt();
                System.out.println("Do you have any intermediate points ? If yes then give the count of intermediate points. Else enter 0");
                int inter = sc.nextInt();
                int[] intermediate;
                if (inter > 0) {
                    intermediate = new int[inter + 1];
                    for (int i = 0; i < inter; i++) {
                        System.out.println("Enter the " + (i + 1) + "th intermediate point");
                        intermediate[i] = sc.nextInt();
                    }
                } else intermediate = new int[1];
                System.out.println("Enter destination");
                intermediate[intermediate.length - 1] = sc.nextInt();
//                code for Dijikstras
                findDistance d= new findDistance();
                for (int j : intermediate) {
                    d.find(source, j); // calling find method from findDistance class
                    source = j;
                }
                System.out.println("Thanks");
                break;
            }

            else if (choice == 2) {
                System.out.println("So you have opted for the tour package");
                printMenu();
                System.out.println("Please enter the number of cities you want to visit");
                int no = sc.nextInt();
                int[] tourCities = new int[no];
                for (int i = 0; i < no; i++) {
                    System.out.println("Please enter the " + (i + 1) + "th city.");
                    tourCities[i] = sc.nextInt();
                }
                //code for travelling shales man
                float[][] NewMatrix =  modifyMatrix(tourCities);
                String [] newList = newNames(tourCities);
                trave.find(NewMatrix,no,newList);  // cqlling find method from travel class
                System.out.println("Thanks");
                break;
            }
            else {
                System.out.println("Please enter a valid option");
                Thread.sleep(2000); //waits for two second
            }
        }
    }
}
