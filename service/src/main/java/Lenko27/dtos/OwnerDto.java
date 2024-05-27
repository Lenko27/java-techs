package Lenko27.dtos;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerDto {
    private String name;
    private LocalDate dob;
    private String password;
}