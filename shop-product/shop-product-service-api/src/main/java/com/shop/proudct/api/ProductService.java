package com.shop.proudct.api;

import com.shop.product.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("product")
public interface ProductService {

     @GetMapping("/findById/{id}")
     @ResponseBody
     public Product findProductById(@PathVariable("id") int id);
}
