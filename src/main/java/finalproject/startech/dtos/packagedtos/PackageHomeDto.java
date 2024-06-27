package finalproject.startech.dtos.packagedtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PackageHomeDto {
    private Long id;
    private String title;
    private String subTitle;
    private float price;
    private List<String> offers;
}
