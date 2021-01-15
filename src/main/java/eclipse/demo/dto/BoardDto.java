package eclipse.demo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
public class BoardDto {

    private Long id;

    private String title;

    private String content;

    private int viewCnt;

    public BoardDto(Long id, String title, String content, int viewCnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
    }


//    public static BoardDto boardDto(Long id, String title, String content, int viewCnt){
//
//        BoardDto boardDto = new BoardDto();
//        boardDto.setId(id);
//        boardDto.setTitle(title);
//        boardDto.setContent(content);
//        boardDto.setViewCnt(viewCnt);
//
//        return boardDto;
//    }
}
