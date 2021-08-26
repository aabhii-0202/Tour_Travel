public class trave {
    static int MAX=100;
    static final int infinity=9999;

    public static void find(float[][] c,int n,String[] cities){ //n= no of cities
        float cost=infinity;
        int[] tour =new int[MAX];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
            {
                if(c[i][j]==0)
                    c[i][j]=infinity;
            }}
        for(int i=0;i<n;i++) tour[i]=i;
        cost=tspdp(c,tour,0,n);
        System.out.println("Mintourcost:"+cost);
        System.out.println("\nTour:");
        for(int i=0;i<n;i++)
            System.out.print(cities[tour[i]] + " -> ");
        System.out.print(cities[tour[0]] + "\n");

    }
    static float tspdp(float[][] c, int[] tour, int start, int n) {
        int i, j, k;
        int[] temp = new int[MAX];
        int[] mintour = new int[MAX];
        float mincost, cost;

        if (start == n - 2) return c[tour[n - 2]][tour[n - 1]] + c[tour[n - 1]][0];

        mincost = infinity;
        for (i = start + 1; i < n; i++) {
            for (j = 0; j < n; j++)
                temp[j] = tour[j];
            temp[start + 1] = tour[i];
            temp[i] = tour[start + 1];
            if (c[tour[start]][tour[i]] + (cost = tspdp(c, temp, start + 1, n)) < mincost) {
                mincost = c[tour[start]][tour[i]] + cost;
                for (k = 0; k < n; k++)
                    mintour[k] = temp[k];
            }
        }

        for (i = 0; i < n; i++) tour[i] = mintour[i];
        return mincost;
    }
}
