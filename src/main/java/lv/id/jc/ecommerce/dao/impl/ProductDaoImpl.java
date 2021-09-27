package lv.id.jc.ecommerce.dao.impl;

import lv.id.jc.ecommerce.dao.ProductDao;
import lv.id.jc.ecommerce.entity.Product;
import lv.id.jc.ecommerce.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNullElse;

public class ProductDaoImpl implements ProductDao {

    private final StoreRepository repository;

    public ProductDaoImpl(final StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> getById(final int id) {
        return Optional.ofNullable(repository.getProductById(id));
    }

    @Override
    public List<Product> getAll() {
        return requireNonNullElse(repository.getAllProducts(), emptyList());
    }

    @Override
    public boolean update(final Product product) {
        return repository.update(product);
    }
}
