package com.example.mywms.Controller;

import com.example.mywms.Model.Product;
import com.example.mywms.Model.ProductStatus;
import com.example.mywms.Model.ProductType;
import com.example.mywms.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/allProducts")
    public String getALlProducts(Model model){
        List<Product> allProducts = productService.findAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "productTemplates/allProducts";
    }

    @GetMapping("/sortedProducts")
    public String getSortedProducts(@RequestParam(value = "FOOD", required = false) boolean isFoodOn,
                                    @RequestParam(value = "JEWELRY", required = false) boolean isJewelryOn,
                                    @RequestParam(value = "CLOTHES", required = false) boolean isClothesOn,
                                    @RequestParam(value = "BEATING", required = false) boolean isBeatingOn,
                                    Model model){
        Set<ProductType> productTypes = new HashSet<>();

        if(isFoodOn){
            productTypes.add(ProductType.FOOD);
        }
        if(isJewelryOn){
            productTypes.add(ProductType.JEWELRY);
        }
        if(isClothesOn){
            productTypes.add(ProductType.CLOTHES);
        }
        if(isBeatingOn){
            productTypes.add(ProductType.BEATING);
        }

        List<Product> productsWithSortedType = productService.findByProductType(productTypes);

        model.addAttribute("sortedProducts", productsWithSortedType);

        return "productTemplates/sortedProducts";
    }

    @GetMapping("/productsManage")
    public String productManagePage(Model model){
        List<Product> allProducts = productService.findAllProducts();
        model.addAttribute("allProducts", allProducts);
        return "productTemplates/productsManage";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        model.addAttribute("productForm", new Product());

        return "productTemplates/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPostMapping(@ModelAttribute("productForm") @Valid Product productForm,
                                        BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "productTemplates/addProduct";
        }

        productForm.setProductStatus(ProductStatus.FREE_TO_DELIVER);

        if (!productService.saveProduct(productForm)){
            model.addAttribute("productNameError", "Такой товар уже существует");
            return "productTemplates/addProduct";
        }

        return "redirect:/allProducts";
    }

}
