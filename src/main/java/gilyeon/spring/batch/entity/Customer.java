package gilyeon.spring.batch.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter
@Setter
//@Table(name = "customer")
@Data
public class Customer {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String name;

    public Customer(String name) {
        this.name = name;
    }
}
