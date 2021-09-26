package lv.id.jc.ecommerce.repository;

import lv.id.jc.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface EcommerceRepository {
    Optional<Product> getProductById(int id);

    List<Product> getAllProducts();
}
