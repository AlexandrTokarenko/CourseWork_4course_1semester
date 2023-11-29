package com.example.coursework.dto;

import com.example.coursework.entity.Realty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RealtyHouseDTO {
    private Integer id;
    @Length(max = 500,min = 10, message = "Мінімальна довжина 10 символів, максимальна - 500")
    private String additionalInformation;
    @NotNull(message = "Поле повинно бути вибрано")
    private Boolean furnishing;
    @Length(min = 10, max = 50,message = "Мінімальна довжина заголовку 10 символів, максимальна - 50 символів")
    @NotBlank(message = "Заголовок не повинен бути порожнім")
    private String header;
    @NotNull(message = "Поле не повинно бути порожнім")
    @Min(value = 1,message = "Площа повинна бути більша 0")
    private Float housingArea;
    @NotNull(message = "Поле не повинно бути порожнім")
    @Min(value = 1,message = "Кількість кімнат повинна бути більша 0")
    private Integer numberOfRooms;
    @NotNull(message = "Поле не повинно бути порожнім")
    @Length(max = 3,min = 3,message = "Мінімальна довжина 3")
    private String currency;
    @NotNull(message = "Поле не повинно бути порожнім")
    @Min(value = 1,message = "Ціна повинна бути більша 0")
    private Integer price;
    @NotNull(message = "Поле повинно бути вибрано")
    private String regionTitle;
    @Min(value = 1, message = "Поле повинно бути вибрано")
    private Integer localityId;
    private Integer districtId;
    @NotNull(message = "Поле повинно бути вибрано")
    private Integer constructionId;
    @NotNull(message = "Поле повинно бути вибрано")
    private Integer heatingId;
    private String propertyTitle;
    @NotNull(message = "Поле не повинно бути порожнім")
    private String purposeTitle;
    @NotNull(message = "Поле повинно бути вибрано")
    @NotBlank(message = "Поле повинно бути вибрано")
    private String repairTitle;
    @NotNull(message = "Поле не повинно бути порожнім")
    @Min(value = 1, message = "Площа земельної ділянки повинна бути більша 0")
    private Float landArea;
    @Min(value = 1,message = "Кількість поверхів в будинку повинно бути більше 0")
    @NotNull(message = "Поле не повинно бути порожнім")
    private Integer numberOfFloors;

    @NotNull(message = "Поле не повинно бути порожнім")
    private MultipartFile[] imageFiles;
    public static RealtyHouseDTO of(Realty realty) {

        return RealtyHouseDTO.builder()
                .id(realty.getId())
                .additionalInformation(realty.getAdditionalInformation())
                .furnishing(realty.getFurnishing())
                .header(realty.getHeader())
                .housingArea(realty.getHousingArea())
                .numberOfRooms(realty.getNumberOfRooms())
                .price(realty.getPrice())
                .regionTitle(realty.getAddress().getRegion().getTitle())
                .localityId(realty.getAddress().getLocality().getId())
                .districtId(realty.getAddress().getDistrict() == null ? null: realty.getAddress().getDistrict().getId())
                .constructionId(realty.getConstruction().getId())
                .heatingId(realty.getHeating().getId())
                .propertyTitle(realty.getProperty().getTitle())
                .purposeTitle(realty.getPurpose().getTitle())
                .repairTitle(realty.getRepair().getTitle())
                .landArea(realty.getHouse().getLandArea())
                .numberOfFloors(realty.getHouse().getFloors())
                .currency(realty.getCurrency().name()).build();
    }
}