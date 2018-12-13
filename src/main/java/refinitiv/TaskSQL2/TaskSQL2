--1. Find any customers that ordered more than 200 tones of the product and is from the UK.
SELECT * FROM ORDERS WHERE Amount > 200 AND Country = 'UK';

--2. Update the type of the product to corn for client with id=2
UPDATE ORDERS SET ORDERS.Type = 'corn' WHERE ID = 2;
-- 'Type' is also a keyword in SQL.

--3. Get the total amount for UK
SELECT SUM(Amount) AS TotalAmountUK FROM ORDERS WHERE Country = 'UK' GROUP BY Country;