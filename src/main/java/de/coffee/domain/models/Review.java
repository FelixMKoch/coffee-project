package de.coffee.domain.models;




import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "coffee_id")
    private Long coffeeId;

    @Column(name = "comment", length = 2048)
    private String comment;

    @Column(name = "create_date")
    private Date createDate;

    //From 1 to 10
    @Column(name = "rating")
    private Integer rating;

    @Column(name = "price")
    private BigDecimal price;


}
