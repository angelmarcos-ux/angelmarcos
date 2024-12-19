package com.example.ecommerce.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    private Integer quantity;
    private BigDecimal price;
    
    @PrePersist
    public void prePersist() {
        if (product != null && price == null) {
            this.price = product.getPrice();
        }
    }
    
    public BigDecimal getSubtotal() {
        return price.multiply(new BigDecimal(quantity));
    }
}
