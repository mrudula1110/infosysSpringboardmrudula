import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "plans")
public class Plan {
    @Id
    private String id;
    private String planType;
    private String planName;
    private String planDescription;
}