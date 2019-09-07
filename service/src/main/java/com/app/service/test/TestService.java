package com.app.service.test;

import com.app.enums.Category;
import com.app.excetption.MyUncheckedException;
import com.app.model.Customer;
import com.app.model.CustomerWithProducts;
import com.app.model.Product;
import com.app.service.ShoppingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TestService {

    private final ShoppingService shoppingService = new ShoppingService("TestFile01.json", "TestFile02.json");

    @Test
    @DisplayName("Test -> shouldThrowExceptionForWrongArgInConstructor")
    public void shouldThrowExceptionForWrongArgInConstructor() {

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> new ShoppingService(null));
        Assertions.assertEquals(" wrong args in ShoppingService constructor", e.getMessage());
    }

    @Test
    @DisplayName("Test -> shouldReturnCorrectNumberOfProduct")
    public void shouldReturnCorrectSizeOfContainer() {

        Map<Customer, Map<Product, Long>> testMap = shoppingService.getCustomersWithProducts();
        System.out.println(testMap.size());
        Assertions.assertEquals(6, testMap.size());

    }

    @Test
    @DisplayName("Test -> shouldReturnWrongSizeOfContainer")
    public void shouldReturnWrongSizeOfContainer() {

        Map<Customer, Map<Product, Long>> testMap = shoppingService.getCustomersWithProducts();
        System.out.println(testMap.size());
        Assertions.assertNotEquals(0, testMap.size());

    }

    @Test
    @DisplayName("Test -> shouldReturnWrongNumberOfCustomer")
    public void shouldReturnWrongNumberOfCustomer() {
        Map<Customer, Map<Product, Long>> testMap = shoppingService.getCustomersWithProducts();
        long numberOfCustomerInMap = testMap.entrySet().stream().map(Map.Entry::getKey).count();
        Assertions.assertNotEquals(0, numberOfCustomerInMap);
    }

    @Test
    @DisplayName("Test -> shouldReturnCorrectNumberOfCustomer")
    public void shouldReturnCorrectNumberOfCustomer() {
        Map<Customer, Map<Product, Long>> testMap = shoppingService.getCustomersWithProducts();
        long numberOfCustomerInMap = testMap.entrySet().stream().map(Map.Entry::getKey).count();
        Assertions.assertEquals(6, numberOfCustomerInMap);
    }

    @Test
    @DisplayName("Test -> shouldReturnWrongNumberOfProduct")
    public void shouldReturnWrongNumberOfProduct() {
        Map<Customer, Map<Product, Long>> testMap = shoppingService.getCustomersWithProducts();
        long numberOfCustomerInMap = testMap.entrySet().stream().map(Map.Entry::getValue).count();
        Assertions.assertNotEquals(0, numberOfCustomerInMap);
    }

    @Test
    @DisplayName("Test -> shouldReturnCorrectNumberOfProduct")
    public void shouldReturnCorrectNumberOfProduct() {
        Map<Customer, Map<Product, Long>> testMap = shoppingService.getCustomersWithProducts();
        long numberOfCustomerInMap = testMap.entrySet().stream().map(Map.Entry::getValue).count();
        Assertions.assertEquals(6, numberOfCustomerInMap);
    }

    @Test
    @DisplayName("Test -> test method paidMostInSelectedCategory with wrong arg")
    public void testMethodPaidMostInSelectedCategory() {
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, () -> shoppingService.paidMostInSelectedCategory(null));
        Assertions.assertEquals("null arg in method paidMostInSelectedCategory", e.getMessage());

    }

    @Test
    @DisplayName("Test -> test result of method whoPaidTheMost")
    public void testMethodWhoPaidTheMost() {
        Customer customerTest = shoppingService.whoPaidTheMost();
        Assertions.assertAll(
                () -> Assertions.assertEquals(customerTest.getName(), "AdamKowalski"),
                () -> Assertions.assertEquals(customerTest.getAge(), 21),
                () -> Assertions.assertEquals(customerTest.getCash(), new BigDecimal(642)),
                () -> Assertions.assertNotEquals(customerTest.getCash(), new BigDecimal(100))
        );

    }

    @Test
    @DisplayName("Test - > showAveragePriceInCategory")
    public void testMethodShowAveragePriceInCategory() {
        Map<Category, BigDecimal> mapTestResult = shoppingService.showAveragePriceInCategory();
        Assertions.assertAll(
                () -> Assertions.assertEquals(mapTestResult.size(), 3),
                () -> Assertions.assertEquals(mapTestResult.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getValue(), new BigDecimal(22294)),
                () -> Assertions.assertNotEquals(mapTestResult.isEmpty(), true)
        );
    }

    @Test
    @DisplayName("Test - > mostExpProductInCategory")
    public void testMethodMostExpProductInCategory() {
        Map<Category, Product> mapTestResult = shoppingService.mostExpProductInCategory();
        System.out.println(mapTestResult);
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(mapTestResult.size(), 10),
                () -> Assertions.assertEquals(mapTestResult.entrySet().stream().max(Comparator.comparing(m -> m.getValue().getPrice())).get().getKey(), Category.ELECTRONIC),
                () -> Assertions.assertEquals(mapTestResult.entrySet().stream().max(Comparator.comparing(m -> m.getValue().getPrice())).get().getValue().getName(), "GameBoy"),
                () -> Assertions.assertNotEquals(mapTestResult.isEmpty(), true)
        );
    }

    @Mock
    private ShoppingService shoppingServiceMock;

    @Test
    @DisplayName("Test test mock ShoppingService")
    public void mockShoppingService() {

        shoppingServiceMock.clearAllFromShoppingService();
        Mockito.verify(shoppingServiceMock).clearAllFromShoppingService();
        Mockito.when(shoppingServiceMock.getSize()).thenReturn(100);
        Assertions.assertEquals(100, shoppingServiceMock.getSize());

    }

    @Spy
    private ShoppingService shoppingServiceSpy;

    @Test
    @DisplayName("Test test mock ShoppingService")
    public void spyShoppingService() {

        shoppingServiceSpy.addCustomersWithProducts(CustomerWithProducts.builder().customer(Customer.builder().name("KAROL").build()).build());
        shoppingServiceSpy.addCustomersWithProducts(CustomerWithProducts.builder().customer(Customer.builder().name("MAJOR").build()).build());
        shoppingServiceSpy.addCustomersWithProducts(CustomerWithProducts.builder().customer(Customer.builder().name("OLAF").build()).build());
        System.out.println(shoppingServiceSpy.getSize());
        Assertions.assertEquals(3, shoppingServiceSpy.getSize());


    }


}
