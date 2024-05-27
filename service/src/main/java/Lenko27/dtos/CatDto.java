package Lenko27.dtos;

import Lenko27.enums.Breeds;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatDto {
    private String name;
    private LocalDate dob;
    private Breeds breed;
    private String color;
    private Long ownerId;
}