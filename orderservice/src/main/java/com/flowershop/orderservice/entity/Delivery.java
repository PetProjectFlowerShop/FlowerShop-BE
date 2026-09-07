package com.flowershop.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type", nullable = false)
    private DeliveryType type;

    private String city;
    private String street;
    private String buildingNumber;
    private String entranceNumber;
    private String apartmentNumber;

    @Column(name = "pickup_store_address")
    private String pickupStoreAddress;
    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;
    @Column(name = "delivery_time_slot", nullable = false)
    private String deliveryTimeSlot;
    @Column(columnDefinition = "TEXT")
    private String deliveryComment;
}
