package lv.id.jc.ecommerce.dao.impl

import lv.id.jc.ecommerce.dao.ProductDao
import lv.id.jc.ecommerce.entity.Product
import lv.id.jc.ecommerce.repository.EcommerceRepository
import spock.lang.Specification

class ProductDaoImplTest extends Specification {
    static final ID_ONE = 1
    static final UNKNOWN_ID = 99
    static final PRODUCT_ONE = new Product(ID_ONE)

    EcommerceRepository repository = Stub()
    ProductDao underTest

    void setup() {
        underTest = new ProductDaoImpl(repository)
    }

    def "should return empty optional for unknown id"() {
        given:
        repository.getProductById(UNKNOWN_ID) >> Optional.empty()

        when:
        def result = underTest.getProductById(UNKNOWN_ID)

        then:
        result.isEmpty()
    }

    def "should return product for existing id"() {
        given:
        repository.getProductById(ID_ONE) >> Optional.of(PRODUCT_ONE)

        when:
        def result = underTest.getProductById(ID_ONE)

        then:
        result.isPresent()
        result.get() == PRODUCT_ONE
    }

    def "GetAllProducts"() {
    }
}
