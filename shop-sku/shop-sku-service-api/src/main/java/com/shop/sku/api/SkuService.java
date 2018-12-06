package com.shop.sku.api;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("sku")
public interface SkuService {

    @PostMapping("/findSku/{id}")
    @ResponseBody
    public Map findSku(@PathVariable("id") int id);
}
