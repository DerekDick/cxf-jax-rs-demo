package org.jianfengderek.cxfjaxrsdemo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private static final List<Product> productList = new ArrayList<Product>();
    static {
        productList.add(new Product(1L, "iphone63", 5000.0));
        productList.add(new Product(2L, "ipad mini", 2500.0));
    }

    @Override
    public List<Product> retrieveAllProducts() {

        Collections.sort(productList, new Comparator<Product>() {

            @Override
            public int compare(Product p1, Product p2) {
                return (p1.getId() > p2.getId()) ? -1 : 1;
            }
        });
        return productList;
    }

    @Override
    public Product retrieveProductById(long id) {
        Product targetProduct = null;
        for (Product product : productList) {
            if (product.getId() == id) {
                targetProduct = product;
                break;
            }
        }
        return targetProduct;
    }

    @Override
    public List<Product> retrieveProductsByName_param(String name){
        List<Product> targetList = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                targetList.add(product);
            }
        }
        return targetList;
    }

    @Override
    public List<Product> retrieveProductsByName_form(String name) {
        List<Product> targetList = new ArrayList<Product>();
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                targetList.add(product);
            }
        }
        return targetList;
    }

    @Override
    public Product createProduct(Product product) {
        product.setId(new Date().getTime());
        productList.add(product);
        return product;
    }

    @Override
    public Product updateProductById(long id, Map<String, Object> fieldMap) {
        Product product = retrieveProductById(id);
        if (product != null) {
            try {
                for (Map.Entry<String, Object> fieldEntry : fieldMap.entrySet()) {
                    Field field = Product.class.getDeclaredField(fieldEntry
                            .getKey());
                    field.setAccessible(true);
                    field.set(product, fieldEntry.getValue());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public Product updateProduct(Product product){
        if(product != null){
            Product targetProduct = retrieveProductById(product.getId());
            if(targetProduct != null){
                targetProduct.setName(product.getName());
                targetProduct.setPrice(product.getPrice());
            }

            return targetProduct;
        }

        return null;
    }

    @Override
    public Product deleteProductById(long id) {
        Product targetProduct = null;
        Iterator<Product> it = productList.iterator();
        while (it.hasNext()) {
            Product product = it.next();
            if (product.getId() == id) {
                targetProduct = product;
                it.remove();
                break;
            }
        }
        return targetProduct;
    }

    @Override
    public Product deleteProductById_param(long id){
        Product targetProduct = null;
        Iterator<Product> it = productList.iterator();
        while (it.hasNext()) {
            Product product = it.next();
            if (product.getId() == id) {
                targetProduct = product;
                it.remove();
                break;
            }
        }
        return targetProduct;
    }

}
