package com.example.mywms.Service;

import com.example.mywms.Model.Delivery;
import com.example.mywms.Model.Product;
import com.example.mywms.Model.ProductStatus;
import com.example.mywms.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductByName(String productName){
        return productRepository.findByProductName(productName);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product findProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }


    /**
     *
     * @param delivery - доставка, через которую будут доставляться товары
     * @param product - товыры, которые будут помещаться в доставку
     * @param productAmount - количесто доставялемого товара
     * @return TRUE в случае, если товыры находятся в статусе FREE_TO_DELIVER и количество товаров на складе
     *         не меньше доставяемого товара
     */
    public boolean putProductInDelivery(Delivery delivery, Product product, int productAmount){
        if (product.getProductStatus().equals(ProductStatus.FREE_TO_DELIVER)){

            if (product.getProductStock() > productAmount) {
                delivery.getProductsForDelivery().add(product);
                product.getDeliveriesProductIn().add(delivery);

                product.setProductStock(product.getProductStock() - productAmount);
                return true;
            }

        }

        return false;
    }


}
