package Lenko27.responses;

import Lenko27.entities.Cat;
import Lenko27.entities.Owner;
import Lenko27.enums.Breeds;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatResponse {
    private String name;
    private LocalDate dob;
    private Breeds breed;
    private String color;
}