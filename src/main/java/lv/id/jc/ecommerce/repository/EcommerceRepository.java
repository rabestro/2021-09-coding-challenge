package lv.id.jc.ecommerce.repository;

import lv.id.jc.ecommerce.entity.Cart;
import lv.id.jc.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface EcommerceRepository {
    Optional<Product> getProductById(int id);

    Optional<Cart> getCartById(int id);

    List<Product> getAllProducts();

    List<Cart> getAllCart();

    boolean update(Record record);
}
