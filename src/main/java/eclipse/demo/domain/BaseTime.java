package eclipse.demo.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public class BaseTime {


    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
