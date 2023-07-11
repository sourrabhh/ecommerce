package com.app.ecommerce.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecommerce.DTO.Cart.AddToCartDto;
import com.app.ecommerce.DTO.Cart.CartDto;
import com.app.ecommerce.DTO.Cart.CartItemDto;
import com.app.ecommerce.Model.Cart;
import com.app.ecommerce.Model.Product;
import com.app.ecommerce.Model.User;
import com.app.ecommerce.Repository.CartRepository;

@Service
public class CartService 
{ 
    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) 
    {
        // Validate if Product id is Valid
        Product product = productService.findById(addToCartDto.getProduct_id());

        // Cave the cart
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        final List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        
        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;

        for(Cart cart: cartList)
        {
            CartItemDto cartItemDto = new CartItemDto(cart);
            cartItems.add(cartItemDto);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
        }

        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItem(cartItems);

        return cartDto;
    }
}
