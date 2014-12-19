package org.shop.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.data.Product;
import org.shop.data.Proposal;
import org.shop.initializer.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:context.xml", "classpath:data-initializer.xml", "classpath:aspects.xml"})
public class OrderServiceTests {

    @Autowired
    OrderService orderService;
    
    @Autowired
    DataInitializer dataInitializer;
    
    @Autowired
    ProductService productService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    ProposalService proposalService;
    
    @Test
    public void createOrder() {
        Product galaxy = productService.getProductsByName("Samsung Galaxy Tab").get(0);
        Proposal proposal = proposalService.getProposalsByProduct(galaxy).get(0);
        
        Long orderId = orderService.createOrder(userService.getUserById((long)1), proposal);
    }
}
