SELECT 
    c.CustomerName,
    p.ProductName,
    o.Quantity,
    o.OrderDate
FROM Orders o
INNER JOIN Customers c ON o.CustomerID = c.CustomerID
INNER JOIN Products p ON o.ProductID = p.ProductID
ORDER BY o.OrderDate;