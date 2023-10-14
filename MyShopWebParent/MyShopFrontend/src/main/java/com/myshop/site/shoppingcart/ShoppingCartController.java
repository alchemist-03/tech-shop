package com.myshop.site.shoppingcart;

import com.myshop.common.entity.CartItem;
import com.myshop.common.entity.Customer;
import com.myshop.site.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {


    @Autowired private ShoppingCartService cartService;
    @Autowired private CustomerService customerService;
    @GetMapping("")
    public String listAll(HttpServletRequest request,Model model) {

        List<CartItem> cartItemList = cartService.listCartItem(getAuthenticatedCustomer(request));
        model.addAttribute("cartItemList",cartItemList);
        float costTotal = 0;
        for(var item : cartItemList) {
            costTotal +=item.getSubTotal();
        }
        model.addAttribute("costTotal",costTotal);

        return "cart/shopping_cart";
    }

    public Customer getAuthenticatedCustomer(HttpServletRequest request) {
        Object principal = request.getUserPrincipal();
        if(principal instanceof UsernamePasswordAuthenticationToken
        || principal instanceof RememberMeAuthenticationToken) {
            String email = request.getUserPrincipal().getName();
            return customerService.findByEmail(email);
        }
        return null;
    }
}
