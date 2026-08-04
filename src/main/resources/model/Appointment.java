import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private String customerId;
    private String agentId;
    private String date;
    private String timeSlot;
    private String reason;
    private String status; 
}