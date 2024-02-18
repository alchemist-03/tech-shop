package com.myshop.site.shoppingcart;

import com.myshop.common.entity.CartItem;
import com.myshop.common.entity.Customer;
import com.myshop.common.exception.CustomerNotFoundException;
import com.myshop.site.customer.CustomerService;
import com.myshop.site.oauth.CustomerOAuth2User;
import com.myshop.site.security.CustomerUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
    public String listAll(Model model) throws CustomerNotFoundException {

        List<CartItem> cartItemList = cartService.listCartItem(getAuthenticatedCustomer());
        model.addAttribute("cartItemList",cartItemList);
        float costTotal = 0;
        for(var item : cartItemList) {
            costTotal +=item.getSubTotal();
        }
        model.addAttribute("costTotal",costTotal);

        return "cart/shopping_cart";
    }

    public Customer getAuthenticatedCustomer() throws CustomerNotFoundException {
        return getCustomerLoggedIn(customerService);
    }

    static Customer getCustomerLoggedIn(CustomerService customerService) throws CustomerNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomerUserDetail) {
                CustomerUserDetail customerUserDetail = (CustomerUserDetail) principal;
                return customerUserDetail.getCustomer();
            }else if(principal instanceof OAuth2User) {
                CustomerOAuth2User oAuth2User = (CustomerOAuth2User) principal;
                String email = oAuth2User.getEmail();
                Customer customer = customerService.findByEmail(email);
                return customer;
            }

            else {
                throw new CustomerNotFoundException("No authenticated customer found");
            }
        } else {
            throw new CustomerNotFoundException("No authenticated customer found");
        }
    }
}
