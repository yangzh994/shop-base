package com.shop.product.api.impl;

import com.shop.product.entity.Product;
import com.shop.proudct.api.ProductService;
import com.shop.sku.api.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private SkuService skuService;

    @Override
    public Product findProductById(@PathVariable("id") int id) {

        Map sku = skuService.findSku(id);

        System.out.println("id=====" + sku.get("id"));
        System.out.println("count=====" + sku.get("count"));

        Product product = new Product();
        product.setId(id);
        product.setName("七匹狼灰色夹克");
        product.setDesc("男士专属夹克");
        product.setType("外套");
        product.setSku((int)sku.get("count"));

        return product;
    }
}
