package org.jianfengderek.cxfjaxrsdemo;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class Product implements Serializable {

    private Long id;

    private String name;

    private Double price;

    public Product() {
    }

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equal(id, product.id) &&
                Objects.equal(name, product.name) &&
                Objects.equal(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, price);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
