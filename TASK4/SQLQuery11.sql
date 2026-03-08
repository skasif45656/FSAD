SELECT 
    o.OrderID,
    c.CustomerName,
    (o.Quantity * p.Price) AS OrderValue
FROM Orders o
JOIN Customers c ON o.CustomerID = c.CustomerID
JOIN Products p ON o.ProductID = p.ProductID
WHERE (o.Quantity * p.Price) = (
    SELECT MAX(o.Quantity * p.Price)
    FROM Orders o
    JOIN Products p ON o.ProductID = p.ProductID
);