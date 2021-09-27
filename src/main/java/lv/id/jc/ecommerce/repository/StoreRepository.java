package lv.id.jc.ecommerce.repository;

import lv.id.jc.ecommerce.entity.Cart;
import lv.id.jc.ecommerce.entity.Product;

import java.util.List;

public interface StoreRepository {
    Product getProductById(int id);

    Cart getCartById(int id);

    List<Product> getAllProducts();

    List<Cart> getAllCart();

    boolean update(Record record);
}
