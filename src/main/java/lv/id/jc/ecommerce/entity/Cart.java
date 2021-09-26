package lv.id.jc.ecommerce.entity;

import java.util.List;

public record Cart(int id, List<Product> products) {
}
