package finalproject.startech.dtos.packagedtos;


import finalproject.startech.dtos.offerdtos.OfferDto;
import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PackageDto {
    private Long id;
    private String title;
    private String subTitle;
    private float price;
    private List<OfferDto> offers;
}
