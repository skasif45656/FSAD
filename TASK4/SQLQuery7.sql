IF NOT EXISTS (SELECT * FROM Customers)
BEGIN
    INSERT INTO Customers VALUES
    (1, 'Alice', 'alice@gmail.com'),
    (2, 'Bob', 'bob@gmail.com'),
    (3, 'Charlie', 'charlie@gmail.com');
END
GO