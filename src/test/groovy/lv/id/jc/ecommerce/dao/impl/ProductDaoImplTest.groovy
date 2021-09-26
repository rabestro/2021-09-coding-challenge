package lv.id.jc.ecommerce.dao.impl

import lv.id.jc.ecommerce.dao.ProductDao
import lv.id.jc.ecommerce.entity.Product
import lv.id.jc.ecommerce.repository.EcommerceRepository
import spock.lang.Specification
import spock.lang.Unroll

class ProductDaoImplTest extends Specification {
    static final ID_ONE = 1
    static final ID_TWO = 2
    static final UNKNOWN_ID = 99
    static final PRODUCT_ONE = new Product(ID_ONE)
    static final PRODUCT_TWO = new Product(ID_TWO)

    EcommerceRepository repository = Stub()
    ProductDao underTest

    void setup() {
        underTest = new ProductDaoImpl(repository)
    }

    def "should return empty optional for unknown id"() {
        given: "the repository returns empty optional for unknown id"
        repository.getProductById(UNKNOWN_ID) >> Optional.empty()

        when: "ProductDao requested for unknown id"
        def result = underTest.getProductById(UNKNOWN_ID)

        then: "result is empty optional"
        result.isEmpty()
    }

    def "should return product for existing id"() {
        given: "repository has a product with specified id"
        repository.getProductById(ID_ONE) >> Optional.of(PRODUCT_ONE)

        when: "ProductDao requested for specified id"
        def result = underTest.getProductById(ID_ONE)

        then: "result is present and it is a product with the specified id"
        result.isPresent()
        result.get() == PRODUCT_ONE
        result.get().id() == ID_ONE
    }

    @Unroll("should return #comment")
    def "should return all products"() {
        given: "the repository has some products"
        repository.allProducts >> products

        when: "ProductDao requested for all products"
        def result = underTest.allProducts

        then: "result has all available products"
        result == products

        where:
        products                   | comment
        []                         | "no products"
        [PRODUCT_ONE]              | "one product"
        [PRODUCT_ONE, PRODUCT_TWO] | "two products"

    }

}
