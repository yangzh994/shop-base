package com.shop.product.api.feign;

import com.shop.sku.api.SkuService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("SHOP-SKU-SERVICE")
public interface SkuServiceRpc extends SkuService{
}
