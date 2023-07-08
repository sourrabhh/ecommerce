package com.app.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecommerce.CommonAPIResponse.ApiResponse;
import com.app.ecommerce.Model.Product;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Model.Wishlist;
import com.app.ecommerce.Repository.WishlistRepository;
import com.app.ecommerce.Service.AuthenticationService;
import com.app.ecommerce.Service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController 
{
     @Autowired 
    WishlistRepository wishlistRepository;

    @Autowired
    AuthenticationService authenticationService;

    // Save Product in wishlist
    @PostMapping("/addtowishlist")
    public ResponseEntity<ApiResponse> addToWishlist(@RequestBody Product product, @RequestParam String token)  
    {
        // Authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // save the item in wishlist
        Wishlist wishlist = new Wishlist(user, product);
        
        WishlistService.createWishlist(wishlist);

        ApiResponse apiResponse = new ApiResponse(true, "Item added in Wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // Get all item of wishlist for a User
}
