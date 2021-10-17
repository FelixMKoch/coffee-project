package de.coffee.domain.models.dtos.custom;

import de.coffee.domain.models.Coffee;
import de.coffee.domain.models.Review;
import de.coffee.domain.models.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDetailFrontendDTO {

    private Review review;

    private Shop shop;

    private Coffee coffee;

}
