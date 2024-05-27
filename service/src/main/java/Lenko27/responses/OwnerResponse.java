package Lenko27.responses;

import Lenko27.entities.Owner;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerResponse {
    private String name;
    private LocalDate dob;
}