package com.example.EStockMarketApplication.Controller;

import com.example.EStockMarketApplication.Controllers.CompanyController;
import com.example.EStockMarketApplication.DTOs.CompanyResponseDTO;
import com.example.EStockMarketApplication.Feign.AuthorizeClient;
import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockExchange;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Service.CompanyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private CompanyController companyController;
    @MockBean
    private CompanyService companyService;

    @Mock
    private AuthorizeClient authorizeClient;

    @Test
    void getAllCompanies() throws Exception {
        // Arrange
        List<CompanyResponseDTO> companies = new ArrayList<>();
        Company company = new Company();
        company.setCompanyName("comp1");
        company.setCompanyCEO("CEO1");
        company.setCompanyTurnover(10000000);
        company.setCompanyWebsite("comp.com");
        company.setStockExchange(StockExchange.NSE);
        StockPrice stockPrice = new StockPrice();
        stockPrice.setCompany(company);
        stockPrice.setPrice(BigDecimal.valueOf(5600.34));
        company.getStockPrice().add(stockPrice);
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);
        companies.add(companyResponseDTO);
        when(companyService.getAllCompanies()).thenReturn(companies);
        when(authorizeClient.authorize("token")).thenReturn(true);
        mockMvc = standaloneSetup(companyController).build();
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/market/company/getAll").header("Authorization","Bearer token").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println("result:"+authorizeClient.authorize("token"));
    }
}