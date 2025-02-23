public class HelloNumbers{
    public static void main (String[] args){
        int x=0;
        while(x<10){
            System.out.print(sum(x)+" ");
            x++;
        }
        System.out.print('\n');
    }
    public static int sum(int x){
        int s=0;
        for(int i=1;i<=x;i++){
            s+=i;
        }
        return s;
    }
}
