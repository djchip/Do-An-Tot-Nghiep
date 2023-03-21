package com.vn.ecommerce.controller;

import com.vn.ecommerce.constants.ResponseFontendDefine;
import com.vn.ecommerce.dto.ResponseModel;
import com.vn.ecommerce.entities.Product;
import com.vn.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("products")
@Slf4j
@CrossOrigin("http://localhost:4200")
public class ProductController extends BaseController {
    private final String START_LOG = "Product {}";
    private final String END_LOG = "Product {} finished in: {}";

    @Autowired
    ProductService productService;

    @GetMapping("find-all")
    public ResponseModel getListDocumentType() {
        final String action = "getListDocumentType";
        StopWatch sw = new StopWatch();
        sw.start();
        log.info(START_LOG, action);
        try {
            ResponseModel responseModel = new ResponseModel();
            responseModel.setContent(productService.findAll());
            responseModel.setStatusCode(HttpStatus.SC_OK);
            responseModel.setCode(ResponseFontendDefine.CODE_SUCCESS);
            return responseModel;
        } catch (Exception e) {
            throw handleException(e);
        } finally {
            log.info(END_LOG, action, sw.getTotalTimeSeconds());
        }
    }

    @PostMapping()
    public ResponseModel doCreate(@RequestBody Product entity) {
        final String action = "doCreate";
        StopWatch sw = new StopWatch();
        log.info(START_LOG, action);
        try {
                entity.setCreatedDate(LocalDateTime.now());
                entity.setUpdatedDate(LocalDateTime.now());
                entity.setCreatedBy("chien");
                entity.setUpdatedBy("chien");
                entity.setActive(true);
                entity = productService.create(entity);

                ResponseModel responseModel = new ResponseModel();
                responseModel.setContent(entity);
                responseModel.setStatusCode(HttpStatus.SC_OK);
                responseModel.setCode(ResponseFontendDefine.CODE_SUCCESS);
                return responseModel;

        } catch (Exception e) {
            System.out.println(e);
            throw handleException(e);
        } finally {
            log.info(END_LOG, action, sw.getTotalTimeSeconds());
        }
    }
}
