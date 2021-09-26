package lv.id.jc.ecommerce.dao.impl;

import lv.id.jc.ecommerce.dao.ProductDao;
import lv.id.jc.ecommerce.entity.Product;
import lv.id.jc.ecommerce.repository.EcommerceRepository;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private final EcommerceRepository repository;

    public ProductDaoImpl(final EcommerceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> getProductById(final int id) {
        return repository.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }
}
