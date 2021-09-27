package lv.id.jc.ecommerce.dao.impl;

import lv.id.jc.ecommerce.dao.CartDao;
import lv.id.jc.ecommerce.entity.Cart;
import lv.id.jc.ecommerce.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNullElse;

public class CartDaoImpl implements CartDao {
    private final StoreRepository repository;

    public CartDaoImpl(final StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Cart> getById(final int id) {
        return Optional.ofNullable(repository.getCartById(id));
    }

    @Override
    public List<Cart> getAll() {
        return requireNonNullElse(repository.getAllCart(), emptyList());
    }

    @Override
    public boolean update(final Cart entity) {
        return repository.update(entity);
    }
}
