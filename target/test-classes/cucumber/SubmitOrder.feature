Feature: Purchasing the order from Ecommerce website

Background:
Given I landed on Ecommerce Page

@Regression
Scenario Outline: Positive test of submitting the order

Given User logged in using username <UserName> and password <Password>
When I add the product <ProductName> to cart
And Checkout <ProductName> and submit the order
Then "Thankyou for the order." message is displayed on confirmation page



Examples:
| UserName             | Password    | ProductName |
|123abc123@gmail.com   | Asurion@123 | ZARA COAT 3 |
