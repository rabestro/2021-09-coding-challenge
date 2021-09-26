package lv.id.jc.ecommerce.dao.impl;

import lv.id.jc.ecommerce.dao.ProductDao;
import lv.id.jc.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    @Override
    public Optional<Product> getProductById(final int id) {
        return Optional.empty();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
