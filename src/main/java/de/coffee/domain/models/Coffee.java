package de.coffee.domain.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="coffee")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "type")
    private String type;

    @Column(name = "flavour")
    private String flavour;

    // Inhalt in ml
    @Column(name = "size")
    private Integer size;

    @Column(name = "plastic")
    private boolean plastic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Coffee coffee = (Coffee) o;

        return coffee.brand.equalsIgnoreCase(this.brand)
                && coffee.type.equalsIgnoreCase(this.type)
                && coffee.name.equalsIgnoreCase(this.name)
                && coffee.flavour.equalsIgnoreCase(this.flavour)
                && coffee.size.equals(this.size);
    }

}
