package gilyeon.spring.batch.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    private Long id;

    @Column
    private String name;


}
