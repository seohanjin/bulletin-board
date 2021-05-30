package eclipse.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    private String filename;
    private String fileOriName;
    private String contentType;
    private String filePath;
    private long size;

    @OneToOne(mappedBy = "files")
    private Member member;

    @Builder
    public Files(String filename, String fileOriName, String contentType, String filePath, long size, Member member) {
        this.filename = filename;
        this.fileOriName = fileOriName;
        this.contentType = contentType;
        this.filePath = filePath;
        this.size = size;
        this.member = member;
        member.setFiles(this);
    }

}

