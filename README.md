# Retail Shop

### Discount Unit
This unit covers three cases where we provide specific discount according to user's profile. 
* Customer with two years experience 5%
* Affiliate 10%
* Employee 30%

The discount types you can find at: 
com.example.retail.type.DiscountType

We count discount by this formula:
round(goods total/100) * discount percent
Example:
45 = round(990/100) * 5
 
All discount code you can find at service:
[Discount Service Implementation](https://github.com/VSYD/retailshop/blob/master/src/main/java/com/example/retail/service/DiscountServiceImpl.java)
 

Unit tests you can find at:
[Discount Service Tests](https://github.com/VSYD/retailshop/tree/master/src/test/java/com/example/retail/service)



### Discount Plugin package
In order to add new discount you need to add new class to com.example.retail.discount.
This class must implement Discount interface and provide three aspects about the discount:
* Usergroup - the group for user we have this discount.
* Conditions where we apply discount.
* Discount value. Example 5% etc.


### Run and build
* Maven

mvn package

* Run

java -jar demo-0.0.1-SNAPSHOT.jar



### Spring Boot Unit

[REST Controller](https://github.com/VSYD/retailshop/blob/master/src/main/java/com/example/retail/controller/RetailController.java)

### UML Diagram

![uml](https://github.com/VSYD/retailshop/blob/master/retailshop.png)

[UML](https://github.com/VSYD/retailshop/blob/master/RetailUML.uml)

