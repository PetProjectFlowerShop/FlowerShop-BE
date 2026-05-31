package com.flowershop.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bouquet_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BouquetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "bouquet_packaging",
        joinColumns = @JoinColumn(name = "bouquet_type_id"),
        inverseJoinColumns = @JoinColumn(name = "packaging_type_id")
    )
    private Set<PackagingType> packagingTypes = new HashSet<>();
}
