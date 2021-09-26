package lv.id.jc.ecommerce.facade;

import lv.id.jc.ecommerce.entity.Cart;
import lv.id.jc.ecommerce.entity.Product;

import java.util.List;

public interface StoreFacade {
    List<Product> getAllProducts();

    List<Product> getCartProducts(int cartId);

    boolean addProductToCart(Cart cart, Product product);
}
