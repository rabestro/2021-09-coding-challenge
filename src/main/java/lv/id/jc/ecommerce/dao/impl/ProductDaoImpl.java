package lv.id.jc.ecommerce.dao.impl;

import lv.id.jc.ecommerce.dao.ProductDao;
import lv.id.jc.ecommerce.entity.Product;
import lv.id.jc.ecommerce.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private final StoreRepository repository;

    public ProductDaoImpl(final StoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> getById(final int id) {
        return repository.getProductById(id);
    }

    @Override
    public List<Product> getAll() {
        return repository.getAllProducts();
    }

    @Override
    public boolean update(final Product product) {
        return repository.update(product);
    }
}
