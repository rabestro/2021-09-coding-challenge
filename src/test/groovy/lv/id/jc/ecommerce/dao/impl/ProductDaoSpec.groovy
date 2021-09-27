package lv.id.jc.ecommerce.dao.impl

import lv.id.jc.ecommerce.dao.ProductDao
import lv.id.jc.ecommerce.entity.Product
import lv.id.jc.ecommerce.repository.StoreRepository
import spock.lang.Specification
import spock.lang.Unroll

class ProductDaoSpec extends Specification {
    static final ID_ONE = 1
    static final ID_TWO = 2
    static final UNKNOWN_ID = 99
    static final PRODUCT_ONE = new Product(ID_ONE)
    static final PRODUCT_TWO = new Product(ID_TWO)

    StoreRepository repositoryStub = Stub()
    ProductDao productDao

    void setup() {
        productDao = new ProductDaoImpl(repositoryStub)
    }

    def "should return empty optional for unknown id"() {

        given: "the repository returns empty optional for an unknown id"
        repositoryStub.getProductById(UNKNOWN_ID) >> null

        when: "productDao requested for the unknown id"
        def result = productDao.getById(UNKNOWN_ID)

        then: "result is empty optional"
        result.isEmpty()
    }

    def "should return #product for id=#id"() {

        given: "repository has a product with specified id"
        repositoryStub.getProductById(id) >> product

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

        when: "productDao requested for all products"
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

        given: "the repository is mocked"
        StoreRepository repositoryMock = Mock()

        and: "productDao created with mocked repository"
        def productDao = new ProductDaoImpl(repositoryMock)

        when: "productDao requested to update a product"
        productDao.update(PRODUCT_ONE)

        then: "the mocked repository should call update"
        1 * repositoryMock.update(PRODUCT_ONE)
    }

    def "should return #status for #comment update"() {

        given: "the repository returns status for product update"
        repositoryStub.update(product) >> status

        when: "productDao requested to update a product"
        def result = productDao.update(product)

        then: "method update returns correct status"
        result == status

        where:
        product     | status | comment
        PRODUCT_ONE | true   | "successful"
        PRODUCT_TWO | false  | "unsuccessful"
    }

}
