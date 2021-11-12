package br.com.luizalabs.schedulerequest.domain.data.entity;

import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "send_date", nullable = false)
    private LocalDateTime sendDate;

    @Column(nullable = false)
    @NotEmpty
    @NotNull
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PENDING','SENT','DELETED')", nullable = false)
    private StatusOfSchedule status;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('WHATSAPP','SMS','EMAIL','PUSH')", nullable = false)
    @NotNull
    private TypeToSend type;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name = "addressee_uuid", referencedColumnName = "uuid", foreignKey = @ForeignKey(name = "uuid"), nullable = false)
    @JoinColumn(name = "addressee_uuid", nullable = false)
    private Addressee addressee;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    protected LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    protected LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt == null ? getCreatedAt() : this.updatedAt;
    }
}
