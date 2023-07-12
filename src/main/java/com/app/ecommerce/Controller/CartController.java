package com.app.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.CommonAPIResponse.ApiResponse;
import com.app.ecommerce.DTO.Cart.AddToCartDto;
import com.app.ecommerce.DTO.Cart.CartDto;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Service.AuthenticationService;
import com.app.ecommerce.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController 
{
    @Autowired
    private CartService cartService;

    @Autowired
    AuthenticationService authenticationService;

    // Post Cart API
    @PostMapping("/addtocart")
    public ResponseEntity<ApiResponse> addTOCart(@RequestBody AddToCartDto addToCartDto, 
                                                    @RequestParam String token)
    {
        // Authenticate Token
        authenticationService.authenticate(token);

        // Find The User
        User user = authenticationService.getUser(token);

        // Add in cart
        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to Cart "), HttpStatus.CREATED);
    }

    // GET cart Items

    @GetMapping("/getcart")
    public ResponseEntity<CartDto> getCartItems(@RequestParam String token)
    {
        authenticationService.authenticate(token);

        User user = authenticationService.getUser(token);

        // Get Cart Items
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    // Delete Cart Item for User
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable int cartItemId ,@RequestParam String token) throws Exception
    {
        authenticationService.authenticate(token);

        int userId = authenticationService.getUser(token).getId();

        cartService.deleteCartItem(cartItemId, userId);

        return new ResponseEntity<>(new ApiResponse(true, "Removed from Cart "), HttpStatus.OK);
    }

}