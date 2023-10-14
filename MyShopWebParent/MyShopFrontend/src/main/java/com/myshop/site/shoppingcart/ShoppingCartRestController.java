package com.myshop.site.shoppingcart;

import com.myshop.common.entity.CartItem;
import com.myshop.common.entity.Customer;
import com.myshop.common.exception.CustomerNotFoundException;
import com.myshop.common.exception.ShoppingCartMaxQuantityExceeded;
import com.myshop.site.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class ShoppingCartRestController {

    @Autowired private ShoppingCartService cartService;
    @Autowired private CustomerService customerService;

    @PostMapping("/cart/add/{productId}/{quantity}")
    public String addProductToCart(HttpServletRequest request, @PathVariable("productId") Integer productId,
                                   @PathVariable("quantity") int quantity) {
        try {
            Customer customer = getAuthenticatedCustomer(request);
            int updatedQuantity = cartService.addProduct(customer,productId,quantity);
            return "The product has been added to your shopping cart with a quantity of: " + updatedQuantity;
        } catch (CustomerNotFoundException e) {
            return "You must login before shopping";
        } catch (ShoppingCartMaxQuantityExceeded e) {
            return e.getMessage();
        }
    }

    public Customer getAuthenticatedCustomer(HttpServletRequest request) throws CustomerNotFoundException {
        Object principal = request.getUserPrincipal();
        if(principal instanceof UsernamePasswordAuthenticationToken
        || principal instanceof RememberMeAuthenticationToken) {
            String email =  request.getUserPrincipal().getName();
            return customerService.findByEmail(email);
        }else {
            throw new CustomerNotFoundException("No authenticated customer found ");
        }
    }

    @PostMapping("/cart/update/{productId}/{quantity}")
    public String updateQuantity(HttpServletRequest request, @PathVariable("productId") Integer productId,
                                   @PathVariable("quantity") int quantity) {
        try {
            Customer customer = getAuthenticatedCustomer(request);
            float updatedQuantity = cartService.updateQuantity(customer,productId,quantity);


            return String.valueOf(updatedQuantity);
        } catch (CustomerNotFoundException e) {
            return "You must login before shopping";
        }
    }

    @DeleteMapping("/cart/remove/{cartItemId}")
    public void removeCartItem(@PathVariable("cartItemId") Integer id) {
        cartService.removeCartItem(id);
    }
}