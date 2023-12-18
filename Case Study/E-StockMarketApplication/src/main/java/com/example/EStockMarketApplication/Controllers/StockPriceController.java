package com.example.EStockMarketApplication.Controllers;

import com.example.EStockMarketApplication.Feign.AuthorizeClient;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1.0/market/stock")
public class StockPriceController {
    private StockPriceService stockPriceService;
    private AuthorizeClient authorizeClient;

    @Autowired
    public StockPriceController(StockPriceService stockPriceService,AuthorizeClient authorizeClient) {
        this.stockPriceService = stockPriceService;
        this.authorizeClient=authorizeClient;
    }

    @PostMapping("/add/{companyCode}")
    public ResponseEntity<String> addNewPrice(@PathVariable long companyCode, @RequestBody StockPrice price, @RequestHeader ("Authorization") String token)
    {
         if(authorizeClient.authorize(token) && authorizeClient.getrole(token).equalsIgnoreCase("admin")) {
             stockPriceService.UpdateStockPrice(companyCode, price);
             return ResponseEntity.ok("New Price Updated for the Company");
         }
         else
         {
             return new ResponseEntity<>("Sorry, can only accessed by Admin", HttpStatus.BAD_REQUEST);
         }
    }
}
