package eclipse.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String filename;
    private String fileOriName;
    private String fileUrl;

    public Files(String filename, String fileOriName, String fileUrl) {
        this.filename = filename;
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
    }
}
