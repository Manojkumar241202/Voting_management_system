import java.util.*;

public class History
{
	public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt()
        int correctOrder[]=new int[n];
        for (int i=0;i<n;i++){
            correctOrder[i]=sc.nextInt();
        }
        int m=sc.nextInt();
        
        for(int st=1;st<=m;st++){
            int order[]=new int[n];
            for(int i=0;i<n;i++){
                order[i]=sc.nextInt();
            }
            
            int[] memo = new int[n + 1];
        for (int i = n - 1; i > -1; i--) {
            int previousRigth = 0, newRight = 0;
            for (int j = n - 1; j > -1; j--) {
                int tmp = memo[j];
                int max;
                if (correctOrder[i] == order[j]) max = previousRigth + 1;
                else max = Math.max(tmp, newRight);
                memo[j] = newRight = max;
                previousRigth = tmp;
            }
        }
        System.out.println(memo[0])
            
        }
	}
}
