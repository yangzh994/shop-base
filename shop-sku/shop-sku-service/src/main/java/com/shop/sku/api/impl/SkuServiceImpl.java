package com.shop.sku.api.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.shop.sku.api.SkuService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class SkuServiceImpl implements SkuService {

    @Override
    public Map findSku(@PathVariable("id") int id) {
        Map map = new HashMap();
        map.put("id",id);
        map.put("count",100);
        return map;
    }
}
