package lv.id.jc.ecommerce.dao.impl;

import lv.id.jc.ecommerce.dao.CartDao;
import lv.id.jc.ecommerce.entity.Cart;
import lv.id.jc.ecommerce.repository.EcommerceRepository;

import java.util.List;
import java.util.Optional;

public class CartDaoImpl implements CartDao {
    private final EcommerceRepository repository;

    public CartDaoImpl(final EcommerceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cart> getById(final int id) {
        return repository.getCartById(id);
    }

    @Override
    public List<Cart> getAll() {
        return repository.getAllCart();
    }

    @Override
    public boolean update(final Cart entity) {
        return repository.update(entity);
    }
}
