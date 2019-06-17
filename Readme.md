# Retail shop

### Discount unit
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
com.example.retail.service.DiscountServiceImpl.processDiscount 

Unit tests you can find at:
com.example.retail.service.DiscountServiceImplTest


### Spring boot unit


