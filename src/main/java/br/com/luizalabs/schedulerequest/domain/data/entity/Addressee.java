package br.com.luizalabs.schedulerequest.domain.data.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "addressee", uniqueConstraints = {@UniqueConstraint(columnNames = {"addressee"})})
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Addressee implements Serializable {

    private static final long serialVersionUID = 8034676095293542006L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @ApiModelProperty(notes = "Name address", name = "addressee", required = true, value = "Name address")
    private String addressee;

    private String email;

    @Column(name = "contact_number")
    @ApiModelProperty(notes = "Contact address", name = "addressee", required = true, value = "(99)9999-9999")
    private String contactNumber;

//    @OneToMany(mappedBy = "addressee", fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "addressee", cascade = CascadeType.ALL, orphanRemoval = true)
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

}
