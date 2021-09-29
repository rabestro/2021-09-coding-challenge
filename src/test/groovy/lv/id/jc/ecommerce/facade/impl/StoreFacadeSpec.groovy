package lv.id.jc.ecommerce.facade.impl

import lv.id.jc.ecommerce.dao.CartDao
import lv.id.jc.ecommerce.dao.ProductDao
import lv.id.jc.ecommerce.entity.Cart
import lv.id.jc.ecommerce.entity.Product
import lv.id.jc.ecommerce.facade.StoreFacade
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class StoreFacadeSpec extends Specification {
    static final ID_ONE = 1
    static final ID_TWO = 2
    static final ID_ADD = 3
    static final UNKNOWN_ID = 99

    static final PRODUCT_ONE = new Product(ID_ONE)
    static final PRODUCT_TWO = new Product(ID_TWO)
    static final PRODUCT_ADD = new Product(ID_ADD)
    static final EMPTY_CART = new Cart(ID_ONE, [])
    static final CART_TWO = new Cart(ID_TWO, [PRODUCT_ONE, PRODUCT_TWO])

    ProductDao mockedProductDao = Mock()
    CartDao mockedCartDao = Mock()

    ProductDao productDao = Stub()
    CartDao cartDao = Stub()

    @Subject
    StoreFacade storeFacade

    void setup() {
        storeFacade = new StoreFacadeImpl(productDao, cartDao)
    }

    def "should thrown an exception"() {
        when:
        storeFacade.addProductToCart(null, PRODUCT_ONE)

        then:
        thrown(NullPointerException)
    }

    @Unroll("should return #comment")
    def "should return all products"() {

        given: "the repository has some products"
        productDao.all >> products

        when: "we requested all products"
        def result = storeFacade.getAllProducts()

        then: "we get all available products"
        result == products

        where:
        products                   | comment
        []                         | "no products"
        [PRODUCT_ONE]              | "one product"
        [PRODUCT_ONE, PRODUCT_TWO] | "two products"
    }

    @Unroll("should return #comment")
    def "should return products for the cart"() {
        given: "cartDao returns cart for specified id"
        cartDao.getById(id) >> Optional.ofNullable(cart)

        when: "we request products for by cart id"
        def result = storeFacade.getCartProducts(id)

        then: "we get all products for the specified cart"
        result == expected

        where:
        id         | cart       || expected              | comment
        ID_ONE     | EMPTY_CART || EMPTY_CART.products() | "no products for an empty cart"
        ID_TWO     | CART_TWO   || CART_TWO.products()   | "two products"
        UNKNOWN_ID | null       || []                    | "no products for unknown cart id"
    }

    @Unroll("should add a product to #comment")
    def "should add product to the cart"() {
        given: "storeFacade with injected dao"
        def storeFacade = new StoreFacadeImpl(mockedProductDao, mockedCartDao)

        when: "we add a product to the cart"
        storeFacade.addProductToCart(cart, product)

        then: "product should not be updated"
        0 * mockedProductDao.update(_)

        and: "the cart should be updated with correct id and products"
        1 * mockedCartDao.update({
            updatedCart ->
                updatedCart.id == cart.id() &&
                        updatedCart.products == expected
        })

        where:
        cart       | comment                | product     || expected
        EMPTY_CART | "an empty cart"        | PRODUCT_ONE || [PRODUCT_ONE]
        CART_TWO   | "a cart with products" | PRODUCT_ADD || [PRODUCT_ONE, PRODUCT_TWO, PRODUCT_ADD]
    }
}
