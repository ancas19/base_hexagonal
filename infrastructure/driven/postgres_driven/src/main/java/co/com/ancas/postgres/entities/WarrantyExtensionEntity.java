package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "warranty_extensions")
public class WarrantyExtensionEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "warranty_id", nullable = false, columnDefinition = "uuid")
    private UUID warrantyId;

    @Column(name = "months_added", nullable = false)
    private Integer monthsAdded;

    @Column(name = "previous_expiration_date", nullable = false)
    private LocalDate previousExpirationDate;

    @Column(name = "new_expiration_date", nullable = false)
    private LocalDate newExpirationDate;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public WarrantyExtensionEntity() {
    }

    public WarrantyExtensionEntity(UUID id, UUID warrantyId, Integer monthsAdded, LocalDate previousExpirationDate, LocalDate newExpirationDate, Instant createdAt) {
        this.id = id;
        this.warrantyId = warrantyId;
        this.monthsAdded = monthsAdded;
        this.previousExpirationDate = previousExpirationDate;
        this.newExpirationDate = newExpirationDate;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(UUID warrantyId) {
        this.warrantyId = warrantyId;
    }

    public Integer getMonthsAdded() {
        return monthsAdded;
    }

    public void setMonthsAdded(Integer monthsAdded) {
        this.monthsAdded = monthsAdded;
    }

    public LocalDate getPreviousExpirationDate() {
        return previousExpirationDate;
    }

    public void setPreviousExpirationDate(LocalDate previousExpirationDate) {
        this.previousExpirationDate = previousExpirationDate;
    }

    public LocalDate getNewExpirationDate() {
        return newExpirationDate;
    }

    public void setNewExpirationDate(LocalDate newExpirationDate) {
        this.newExpirationDate = newExpirationDate;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
