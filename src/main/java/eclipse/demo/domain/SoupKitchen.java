package eclipse.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class SoupKitchen {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soup_kitchen_id")
    private Long id;

    private String name;

    private String newAddress;

    private String oldAddress;

    private String siDo;

    private String gunGu;

    private String operatingName;

    private String phoneNumber;

    private String location;

    private String target;

    private String time;

    private String DoW;

    private String startOperation;

    private String endOperation;

    private String latitude;

    private String longitude;




}
