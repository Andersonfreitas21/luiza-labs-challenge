package br.com.luizalabs.schedulerequest.domain.data.v1.form;

import br.com.luizalabs.schedulerequest.exception.ContactNumberException;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class AddresseeForm {

    @NotBlank(message = "Addressee name cannot be empty or null")
    @ApiModelProperty(notes = "Name address", name = "addressee", required = true, value = "Name address")
    private String addresseeName;

    @Email(message = "Email invalid")
    @NotBlank
    private String email;

    @NotBlank(message = "Addressee contact cannot be empty or null")
    @ApiModelProperty(notes = "Contact address", name = "addressee", required = true, value = "(99)9999-9999")
    private String contactNumber;

    protected boolean check(String contactNumber) throws ContactNumberException {
        if (contactNumber.matches("\\(\\d{2,}\\)\\d{4,}\\-\\d{4}")) {
            this.contactNumber = contactNumber;
            return true;
        }
        throw new ContactNumberException(AddresseeForm.class, contactNumber);
    }
}
