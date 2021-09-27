package lv.id.jc.ecommerce.dao.impl

import lv.id.jc.ecommerce.dao.CartDao
import lv.id.jc.ecommerce.entity.Cart
import lv.id.jc.ecommerce.entity.Product
import lv.id.jc.ecommerce.repository.StoreRepository
import spock.lang.Specification
import spock.lang.Unroll

class CartDaoSpec extends Specification {
    static final ID_ONE = 1
    static final ID_TWO = 2
    static final UNKNOWN_ID = 99
    static final PRODUCT_ONE = new Product(ID_ONE)
    static final PRODUCT_TWO = new Product(ID_TWO)
    static final CART_ONE = new Cart(ID_ONE, [])
    static final CART_TWO = new Cart(ID_TWO, [PRODUCT_ONE, PRODUCT_TWO])

    StoreRepository repositoryStub = Stub()
    CartDao underTest

    void setup() {
        underTest = new CartDaoImpl(repositoryStub)
    }

    def "should return empty optional for unknown id"() {
        given: "the repository returns empty optional for an unknown id"
        repositoryStub.getCartById(UNKNOWN_ID) >> null

        when: "we get a cart by unknown id"
        def result = underTest.getById(UNKNOWN_ID)

        then: "we get an empty optional"
        result.isEmpty()
    }

    def "should return #cart for id=#id"() {

        given: "repository has a cart with specified id"
        repositoryStub.getCartById(id) >> cart

        when: "we requested a cart by the id"
        def result = underTest.getById(id)

        then: "result is present and it is the cart with the specified id"
        result.isPresent()
        result.get() == cart
        result.get().id() == id

        where:
        id     | cart
        ID_ONE | CART_ONE
        ID_TWO | CART_TWO
    }

    @Unroll("should return #comment")
    def "should return all carts"() {

        given: "the repository has some carts"
        repositoryStub.allCart >> carts

        when: "we request for all carts"
        def result = underTest.getAll()

        then: "we get all available carts"
        result == carts

        where:
        carts                | comment
        []                   | "no carts"
        [CART_ONE]           | "one cart"
        [CART_ONE, CART_TWO] | "two carts"
    }

    def "should update a cart"() {
        given: "the repository is mocked"
        StoreRepository repositoryMock = Mock()

        and: "cartDao created with mocked repository"
        def cartDao = new CartDaoImpl(repositoryMock)

        when: "we update the cart"
        cartDao.update(CART_ONE)

        then: "the mocked repository should call update"
        1 * repositoryMock.update(CART_ONE)
    }

    def "should return #status for #comment update"() {

        given: "the repository returns status for cart update"
        repositoryStub.update(cart) >> status

        when: "we update the cart"
        def result = underTest.update(cart)

        then: "we get correct status"
        result == status

        where:
        cart     | status | comment
        CART_ONE | true   | "successful"
        CART_TWO | false  | "unsuccessful"
    }

}
