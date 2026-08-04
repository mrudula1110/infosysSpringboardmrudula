import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User1 {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role; // "ADMIN", "AGENT", "CUSTOMER"
    private String address;
    private String dob;
    private boolean isVerified;
}