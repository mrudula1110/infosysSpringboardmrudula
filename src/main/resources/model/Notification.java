import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "notifications")
public class Notification {
    @Id
    private String id;
    private String userId;
    private String message;
    private String type; // "EMAIL", "SMS", "IN_APP"
    private boolean isRead;
    private String createdAt;
}

/*import java.util.Scanner;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        for(int i=0;i<=nums.length;i++){
            for(int j=i+1;j<=nums.length;j++){
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
        
        System.out.println("enter the size of an  array");
        int n=sc.nextInt();
        int[] num=new int[n];
        
        System.out.println("enter the array elements");
        for (int k = 0; k < n; k++) {
            num[k] = sc.nextInt();
        }
        
        System.out.println("enter the target");
        int tar=sc.nextInt();
        
        Solution sol=new Solution();
        int[] result=sol.twoSum(num,tar);
        
        System.out.println("["+result[0]+","+result[1]+"]");

    }
}*/