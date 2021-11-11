package br.com.luizalabs.schedulerequest.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "addressee")
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class Addressee implements Serializable {

    private static final long serialVersionUID = 8034676095293542006L;
    //uniqueConstraints = {@UniqueConstraint(columnNames = {"addressee"})}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @ApiModelProperty(notes = "Contact address", name = "addressee", required = true, value = "Email or Contact number")
    private String addressee;

    @Email(message = "Invalid email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @OneToMany(mappedBy = "addressee", fetch = FetchType.LAZY)
    private List<Scheduling> schedules;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @PrePersist
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt == null ? getCreatedAt() : this.updatedAt;
    }

    public boolean check(String addressee) {
        if (addressee.contains("@")) {
            this.email = addressee;
            return true;
        } else if (addressee.matches("\\(\\d{2,}\\)\\d{4,}\\-\\d{4}")) {
            this.contactNumber = addressee;
            return true;
        }
        return false;
    }

}
