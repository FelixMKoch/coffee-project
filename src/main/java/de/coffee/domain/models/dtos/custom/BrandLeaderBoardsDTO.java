package de.coffee.domain.models.dtos.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandLeaderBoardsDTO {

    private String brand;

    private Integer times;

    private Integer literSum;

    private Double avgRating;

}
