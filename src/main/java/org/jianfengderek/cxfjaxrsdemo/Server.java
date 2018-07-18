package org.jianfengderek.cxfjaxrsdemo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Server {

    public static void main(String[] args) {
        // Add ResourceClass
        List<Class<?>> resourceClassList = new ArrayList<Class<?>>();
        resourceClassList.add(HelloServiceImpl.class);
        resourceClassList.add(ProductServiceImpl.class);

        // Add ResourceProvider
        List<ResourceProvider> resourceProviderList = new ArrayList<ResourceProvider>();
        resourceProviderList.add(new SingletonResourceProvider(new HelloServiceImpl()));
        resourceProviderList.add(new SingletonResourceProvider(new ProductServiceImpl()));

        // Add providers
        List<Object> providerList = new ArrayList<Object>();
        providerList.add( new JacksonJsonProvider());

        // Publish
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress("http://localhost:8080/ws/rest");
        factory.setResourceClasses(resourceClassList);
        factory.setResourceProviders(resourceProviderList);
        factory.setProviders(providerList);
        factory.create();
        System.out.println("REST ws is published!");
    }

}
