package mk.finki.ukim.mk.lab.service.Imp;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.exception.InvalidBalloonException;
import mk.finki.ukim.mk.lab.repository.jpa.OrderRep;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRep;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {
    private final OrderRep orderRepository;
    private final ShoppingCartRep shoppingCartRep;

    public OrderServiceImp(OrderRep orderRepository, ShoppingCartRep shoppingCartRep) {
        this.orderRepository = orderRepository;
        this.shoppingCartRep = shoppingCartRep;
    }


    @Override
    public Optional<Order> placeOrder(String balloonColor, String balloonSize, LocalDateTime date, Long cartId) {
        ShoppingCart shoppingCart = this.shoppingCartRep.findById(cartId).get();
        return Optional.of(this.orderRepository.save(new Order(balloonColor, balloonSize, date, shoppingCart)));
    }

    @Override
    public List<Order> findAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return  this.orderRepository.findById(orderId);
    }

    @Override
    public Order save(String balloonColor, String balloonSize, LocalDateTime date, Long cartId) {
        if(balloonColor == null || balloonSize == null)
            throw new InvalidBalloonException();
        ShoppingCart shoppingCart = this.shoppingCartRep.findById(cartId).get();
        Order order = new Order(balloonColor, balloonSize, date, shoppingCart);
        return this.orderRepository.save(order);
    }

    @Override
    public List<Order> filterTime(LocalDateTime from, LocalDateTime to) {
        if(from == null || to == null)
            throw new InvalidBalloonException();

        List<Order> orders;
        if(from.compareTo(to) > 0) {
             orders = this.orderRepository.findByDateBetween(from, to);
        }else{
             orders = this.orderRepository.findByDateBetween(to, from);
        }
        return orders;

    }


}
