HOW TO SET UP PROJECT: 
1. Compile all java files.
2. Run App.class

PROGRAM'S USER TYPES:
1. User = Able to view Inventory. Do not require login.
2. Member = Able to view and buy Inventory. Every Customer can become a member. New members get balance of 1000$, 0 reward points, and 3 random discounts. Require memberId to login
3. Admin = Able to view and modify inventory. Require username and password to login

INVENTORY:
1. Product abstract class that hase common fields for all products: name, price, company, quantity
2. The field of name is unique for each product type.
3. Currently, there are 2 product types: Games, Consoles.
4. Games have unique field: Genre
5. Consoles have unique field: library/games that they could run

HOW VIEW WORKS:
1. Everyone could view inventoiry
2. There are 2 options of view: Sort and Filter.

HOW THE PURCHASE WORKS:
1. In order to purchase, user must be type of Member.
2. Member can buy product with money on his balance.
3. Member can buy product with reward points: product price * 100 = reward points price.
4. Member can apply 1 discount available on his purchase.
5. After each purchase, member receives reward points calculated : product price * 10 = reward points.
6. If Member applied the discount, he gets a new one with random percentage.
7. After the succesfull purchase, the quantity of the specific product updates in the inventory.
8. Member can buy only the products with quantity more than 1

HOW MODIFY WORKS
1. Only admins have modify options.
2. Admins could modify all products.
3. Admins could modify all members.
4. Admins could modify their own data, but not the data of others admins.
5. Admins could add new admins.






