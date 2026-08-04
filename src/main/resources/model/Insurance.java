import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "insurance")
public class Insurance {
    @Id
    private String id;
    private String planId;
    private String description;
    private String insuranceType;
    private String premium;
    private String amount;
}