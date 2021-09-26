package lv.id.jc.ecommerce.dao;

import lv.id.jc.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<Product> getProductById(int id);

    List<Product> getAllProducts();
}
