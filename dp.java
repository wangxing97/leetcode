public class dp {
    public static int cutMemo(int []p)
    {
        int []r=new int[p.length+1];
        for(int i=0;i<=p.length;i++)
            r[i]=-1;
        return cut(p, p.length, r);
    }
    public static int cut(int []p,int n,int []r)
    {
        int q=-1;
        if(r[n]>=0)
            return r[n];
        if(n==0)
            q=0;
        else {
            for(int i=1;i<=n;i++)
                q=Math.max(q, cut(p, n-i,r)+p[i-1]);
        }
        r[n]=q;

        return q;
    }

    public static int buttom_up_cut(int []p)
    {
        int []r=new int[p.length+1];
        for(int i=1;i<=p.length;i++)
        {
            int q=-1;
            //①
            for(int j=1;j<=i;j++)
                q=Math.max(q, p[j-1]+r[i-j]);
            r[i]=q;
        }
        return r[p.length];
    }

    /**
     * 在一个夜黑风高的晚上，有n（n <= 50）个小朋友在桥的这边，现在他们需要过桥，但是由于桥很窄，
     * 每次只允许不大于两人通过，他们只有一个手电筒，所以每次过桥的两个人需要把手电筒带回来，
     * i号小朋友过桥的时间为T[i]，两个人过桥的总时间为二者中时间长者。问所有小朋友过桥的总时间最短是多少。
     */
    //递归版,num为剩余的小朋友
    public int dg(int[] child){
        int p = Integer.MAX_VALUE;
        int[] opt = new int[child.length];
        opt[0] = child[0];
        opt[1] = child[1];
        opt[2] = child[0] + child[1] + child[2];
        for (int i = 4; i <= child.length; i++){
            p = Math.min(opt[i-2] + child[0] + child[i-1], child[i-1] + 2 * child[1] + child[0] + opt[i-3]);
            opt[i-1] = p;
        }
        return opt[child.length-1];
    }

    public static void main(String[] args) {
        int[] p = new int[]{1,5,8,9,10,17,17,20,24,30};
        dp dp = new dp();
        int[] q = new int[]{1,2,5,10};
        System.out.println(dp.dg(q));
    }
}
