package basic.data;

/**
 * Created by liubo on 16/7/14.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int a[]={1,54,6,3,78,34,12,45};
        int temp=0;
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]>a[j]){
                    temp=a[i];
                    a[i]=a[j];
                    a[j]=temp;
                }
            }
            System.out.println(a);
        }
        for(int i=0;i<a.length;i++)
            System.out.println(a[i]);
    }
}



