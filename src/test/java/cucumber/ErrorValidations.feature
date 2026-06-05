Feature: Error validation

Background:
Given I landed on Ecommerce Page

@ErrorValidation
Scenario Outline: Positive test of submitting the order

Given I landed on Ecommerce Page
Given   User logged in using username <UserName> and password <Password>
Then "Incorrect email or password." message is displayed on landing page

Examples:
| UserName             | Password   |
|123abc123@gmail.com   | Asurion@13 |
