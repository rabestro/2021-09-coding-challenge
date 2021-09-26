package lv.id.jc.ecommerce.facade.impl;

import lv.id.jc.ecommerce.dao.CartDao;
import lv.id.jc.ecommerce.dao.ProductDao;
import lv.id.jc.ecommerce.entity.Cart;
import lv.id.jc.ecommerce.entity.Product;
import lv.id.jc.ecommerce.facade.StoreFacade;

import java.util.List;

public class StoreFacadeImpl implements StoreFacade {
    private final ProductDao productDao;
    private final CartDao cartDao;

    public StoreFacadeImpl(final ProductDao productDao, final CartDao cartDao) {
        this.productDao = productDao;
        this.cartDao = cartDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @Override
    public List<Product> getCartProducts(final int cartId) {
        return cartDao.getById(cartId).map(Cart::products).orElse(List.of());
    }

    @Override
    public Cart addProductToCart(final Cart cart, final Product product) {
        cart.products().add(product);
        cartDao.update(cart);
        return cart;
    }
}
