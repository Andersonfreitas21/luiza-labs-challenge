package br.com.luizalabs.schedulerequest.domain.data.v1.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class SchedulingForm {

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("send_date")
    @FutureOrPresent(message = "Invalid date entered, enter a date greater than the current date")
    private LocalDateTime sendDate;

    @NotBlank(message = "Addressee cannot be blank")
    private AddresseeForm addressee;

    @NotNull
    private String message;

}
