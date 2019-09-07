package com.app.service;

import com.app.enums.Category;
import com.app.excetption.MyUncheckedException;
import com.app.model.Customer;
import com.app.utility.ScreenManager;

public class ControlAppService {

    private final DataManager dataManager = new DataManager();
    private final ShoppingService shoppingService = new ShoppingService("TestFile01.json", "TestFile02.json");

    public void runApp() {
        do {
            try {
                dataManager.getLine("press ENTER to CONTINUE");
                ScreenManager.printMenu();
                int number = dataManager.getInt("MAKE A CHOICE PRESS FROM 0 TO 6");

                switch (number) {
                    case 0: {
                        ScreenManager.clearScreen2();
                        System.out.println("-------------------GOOD BYE------------------------");
                        return;
                    }
                    case 1: {
                        task1();
                        break;
                    }
                    case 2: {
                        task2();
                        break;
                    }
                    case 3: {
                        task3();
                        break;
                    }
                    case 4: {
                        task4();
                        break;
                    }
                    case 5: {
                        task5();
                        break;
                    }
                    case 6: {
                        printRawData();
                        break;
                    }
                    default: {
                        System.out.println("wrong choice try again");
                        ScreenManager.clearScreen2();
                        break;
                    }
                }
            } catch (MyUncheckedException e) {
                e.printStackTrace();
            }

        } while (true);
    }


    private void printRawData() {
        System.out.println("Loading data.......... \n");
        shoppingService.toString();

    }

    private Category getCategoryFromNumber() {
        String parameter = dataManager.getLine(" Choose category press 1 - BOOK, 2 - ELECTRONIC, 3 - FOOD ");
        if (!parameter.matches("[123]")) {
            throw new MyUncheckedException(" WRONG CATEGORY CHOICE");
        }
        return Category.convertFromNumber(Integer.parseInt(parameter));
    }

    private void task1() {
        System.out.println(" Customer who paid the most for all purchases ");
        Customer result = shoppingService.whoPaidTheMost();
        System.out.println(result);
        Category category = getCategoryFromNumber();
        Customer result2 = shoppingService.paidMostInSelectedCategory(category);
        System.out.println(" Customer who paid the most in Category ->  " + category.toString());
        System.out.println(result2);
    }

    private void task2() {
        System.out.println("Result of task 2A ........map  with age of customers and\n" +
                "     categories of products that were most often purchased at this age");
        shoppingService.mappingByAgeCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
    }

    private void task3() {
        System.out.println("average Price in Category");
        shoppingService.showAveragePriceInCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
        System.out.println("most expansive Product in  Category");
        shoppingService.mostExpProductInCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
        System.out.println("Cheapest product in Category");
        shoppingService.cheapestProductInCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }

    private void task4() {
        System.out.println(" Customer and  Category which is most often choose");
        shoppingService.customersWithCategoryMostChosen().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }

    private void task5() {
        System.out.println("Customers and their debts");
        shoppingService.customersWithDebts().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }
}

