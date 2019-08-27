"# GoodsSelectorMultiApp03" 
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)

## General info
Multi-Module Maven Application with Java Modules , still in progress adding new functionality

## Technologies
* Java - version 12
* gson - version 2.8.4
* maven - version 3.6
* Multi-Module Maven 
* TestUnit junit 4.12
* junit-jupiter

## Setup
download, compile and run, in module main file to compile main-1.0-SNAPSHOT-jar-with-dependencies.jar

## Code Examples
private Customer customerWhoPaidMostInCategory(Category cat) {
                        return customersWithProducts.entrySet()
                                .stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        e -> e.getValue().entrySet().stream().filter(f -> f.getKey().getCategory().equals(cat)).map(m -> m.getKey().getPrice().multiply(new BigDecimal(m.getValue()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                                ))
                                .entrySet()
                                .stream()
                                .max(Comparator.comparing(Map.Entry::getValue))
                                .orElseThrow(NullPointerException::new)
                                .getKey();
                    }

## Features

To-do list:
- cleanCode - optimize code 
- Junit
- Json



## Status
Project is: _in_progress_