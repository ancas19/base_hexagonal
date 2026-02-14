package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "warranties")
public class WarrantyEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "customer_id", nullable = false, columnDefinition = "uuid")
    private UUID customerId;

    @Column(name = "serial_number", length = 100, nullable = false, unique = true)
    private String serialNumber;

    @Column(name = "product_model", length = 150, nullable = false)
    private String productModel;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "original_duration_months", nullable = false)
    private Integer originalDurationMonths;

    @Column(name = "total_duration_months", nullable = false)
    private Integer totalDurationMonths;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public WarrantyEntity() {
    }

    public WarrantyEntity(UUID id, UUID customerId, String serialNumber, String productModel, LocalDate purchaseDate, Integer originalDurationMonths, Integer totalDurationMonths, LocalDate expirationDate, String status, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.serialNumber = serialNumber;
        this.productModel = productModel;
        this.purchaseDate = purchaseDate;
        this.originalDurationMonths = originalDurationMonths;
        this.totalDurationMonths = totalDurationMonths;
        this.expirationDate = expirationDate;
        this.status = status;
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

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getOriginalDurationMonths() {
        return originalDurationMonths;
    }

    public void setOriginalDurationMonths(Integer originalDurationMonths) {
        this.originalDurationMonths = originalDurationMonths;
    }

    public Integer getTotalDurationMonths() {
        return totalDurationMonths;
    }

    public void setTotalDurationMonths(Integer totalDurationMonths) {
        this.totalDurationMonths = totalDurationMonths;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
