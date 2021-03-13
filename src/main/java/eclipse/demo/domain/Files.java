package eclipse.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String filename;
    private String fileOriName;
    private String fileUrl;
    private String contentType;
    private String filePath;
    private long size;

    @OneToOne(mappedBy = "files")
    private Member member;

}

