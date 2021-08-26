public class findDistance {
    private float[] distance;
    private final float[][] cost = tourandTravel.cost ;
    private int dest;
    private final String[] names = tourandTravel.cities;

    public void find(int src,int dest){
        int nodes = names.length;
        this.dest=dest;
        this.distance=new float[nodes +1];
        compute(nodes,src);
    }

    public void compute(int n,int src){
        int i,minpos=1,u,c;
        float minimum;
        int[] flag =new int[n+1];

        for(i=0;i<n;i++) {
            flag[i] = 0;
            this.distance[i] = this.cost[src][i];
        }
        c=2;
        while(c<=n){
            minimum=99999;
            for(u=1;u<n;u++){
                if (this.distance[u]<minimum&&flag[u]!=1){
                    minimum=this.distance[i];
                    minpos=u;
                }
            }
            flag[minpos]=1;
            c++;
            for(u=1;u<n;u++){
                if(this.distance[minpos]+this.cost[minpos][u]<this.distance[u]&&flag[u]!=1)
                    this.distance[u]=this.distance[minpos]+this.cost[minpos][u];
            }
        }
        System.out.println("Distance calculated = "+names[src]+"-->"+names[dest]+" Distance = "+distance[dest]+"km");
    }
}

