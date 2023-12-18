package com.example.EStockMarketApplication.DTOs;

import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockExchange;
import com.example.EStockMarketApplication.Models.StockPrice;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CompanyResponseDTO {

    private Long CompanyCode;
    private String CompanyName;
    private String CompanyCEO;
    private Integer CompanyTurnover;
    private String CompanyWebsite;
    private StockExchange stockExchange;
    private StockPrice LatestStockPrice;

    public CompanyResponseDTO(Company company)
    {
        this.CompanyCode=company.getCompanyCode();
        this.CompanyName=company.getCompanyName();
        this.CompanyCEO=company.getCompanyCEO();
        this.CompanyTurnover=company.getCompanyTurnover();
        this.CompanyWebsite=company.getCompanyWebsite();
        this.stockExchange=company.getStockExchange();
        this.LatestStockPrice=getLatestStockPrice(company);
    }
    private StockPrice getLatestStockPrice(Company company) {
        if (company.getStockPrice() != null && !company.getStockPrice().isEmpty()) {
            return company.getStockPrice().get(company.getStockPrice().size()-1);
        }
        return null;
    }

}