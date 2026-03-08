IF NOT EXISTS (SELECT * FROM Products)
BEGIN
    INSERT INTO Products VALUES
    (101, 'Laptop', 60000),
    (102, 'Mobile', 25000),
    (103, 'Headphones', 3000);
END
GO