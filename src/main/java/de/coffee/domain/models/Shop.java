package de.coffee.domain.models;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="shop_id")
    private Long shopId;

    @Column(name="name")
    private String name;

    @Column(name="place", nullable = true)
    private String place;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        return shop.name.equalsIgnoreCase(this.name) &&
                shop.place.equalsIgnoreCase(this.place);
    }

}
