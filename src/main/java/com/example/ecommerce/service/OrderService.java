package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    public Order createOrder(Long userId, List<OrderItem> items, String shippingAddress, String paymentMethod) {
        User user = userService.getUserById(userId);
        
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(paymentMethod);
        
        // Process each order item
        for (OrderItem item : items) {
            Product product = productService.getProductById(item.getProduct().getId());
            
            // Verify stock availability
            if (!productService.updateStock(product.getId(), item.getQuantity())) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            
            item.setPrice(product.getPrice());
            order.addItem(item);
        }
        
        // Calculate total amount
        order.calculateTotal();
        
        return orderRepository.save(order);
    }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }
    
    public List<Order> getUserOrders(Long userId) {
        User user = userService.getUserById(userId);
        return orderRepository.findByUserOrderByOrderDateDesc(user);
    }
    
    public Order updateOrderStatus(Long orderId, Order.OrderStatus newStatus) {
        Order order = getOrderById(orderId);
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }
    
    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status);
    }
    
    public List<Order> getOrdersInDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findOrdersInDateRange(startDate, endDate);
    }
    
    public BigDecimal calculateOrderTotal(List<OrderItem> items) {
        return items.stream()
            .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
