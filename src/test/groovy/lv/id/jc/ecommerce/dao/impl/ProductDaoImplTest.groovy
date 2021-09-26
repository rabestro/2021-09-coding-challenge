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

    EcommerceRepository repositoryStub = Stub()
    EcommerceRepository repositoryMock = Mock()
    ProductDao productDao

    void setup() {
        productDao = new ProductDaoImpl(repositoryStub)
    }

    def "should return empty optional for unknown id"() {

        given: "the repository returns empty optional for some id"
        repositoryStub.getProductById(_ as int) >> Optional.empty()

        when: "ProductDao requested for this id"
        def result = productDao.getById(UNKNOWN_ID)

        then: "result is empty optional"
        result.isEmpty()
    }

    def "should return #product for id=#id"() {

        given: "repository has a product with specified id"
        repositoryStub.getProductById(id) >> Optional.of(product)

        when: "ProductDao requested for specified id"
        def result = productDao.getById(id)

        then: "result is present and it is a product with the specified id"
        result.isPresent()
        result.get() == product
        result.get().id() == id

        where:
        id     | product
        ID_ONE | PRODUCT_ONE
        ID_TWO | PRODUCT_TWO
    }

    @Unroll("should return #comment")
    def "should return all products"() {

        given: "the repository has some products"
        repositoryStub.allProducts >> products

        when: "ProductDao requested for all products"
        def result = productDao.getAll()

        then: "result has all available products"
        result == products

        where:
        products                   | comment
        []                         | "no products"
        [PRODUCT_ONE]              | "one product"
        [PRODUCT_ONE, PRODUCT_TWO] | "two products"
    }

    def "should update the product"() {

        given: "The ProductDao with mocked repository"
        def productDao = new ProductDaoImpl(repositoryMock)

        when: "ProductDao requested to update a product"
        productDao.update(PRODUCT_ONE)

        then: "The repository should call update"
        1 * repositoryStub.update(PRODUCT_ONE)
    }
}
