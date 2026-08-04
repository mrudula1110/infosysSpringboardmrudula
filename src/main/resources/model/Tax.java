import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "taxes")
public class Tax {
    @Id
    private String id;
    private String taxName;
    private String taxType;
    private String taxDescription;
    private String insuranceId;
}