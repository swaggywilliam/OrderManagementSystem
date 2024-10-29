package com.zhuwl.springcloud.service.impl;

import com.zhuwl.springcloud.dao.ProductDao;
import com.zhuwl.springcloud.entity.Product;
import com.zhuwl.springcloud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
