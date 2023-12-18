package com.example.EStockMarketApplication.Service;

import com.example.EStockMarketApplication.Models.StockPrice;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface StockPriceService {
    void UpdateStockPrice(long companyCode, StockPrice newPrice);
}
