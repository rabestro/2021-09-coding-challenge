package lv.id.jc.ecommerce.facade.impl

import lv.id.jc.ecommerce.dao.CartDao
import lv.id.jc.ecommerce.dao.ProductDao
import lv.id.jc.ecommerce.facade.StoreFacade
import spock.lang.Specification

class StoreFacadeSpec extends Specification {
    ProductDao productDao = Stub()
    CartDao cartDao = Stub()

    StoreFacade storeFacade

    void setup() {
        storeFacade = new StoreFacadeImpl(productDao, cartDao)
    }

    def "GetAllProducts"() {
    }

    def "GetCartProducts"() {
    }

    def "AddProductToCart"() {
    }
}
