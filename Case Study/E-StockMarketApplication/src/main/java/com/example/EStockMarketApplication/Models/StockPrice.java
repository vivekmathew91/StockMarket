package com.example.EStockMarketApplication.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private BigDecimal price;
    @Column(updatable = false)
    private LocalDateTime timeStamp;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CompanyCode" ,referencedColumnName = "CompanyCode")
    @JsonBackReference
    private Company company;
    @PrePersist
    protected void onCreate() {
        timeStamp = LocalDateTime.now();
    }
}
