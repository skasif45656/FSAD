IF NOT EXISTS (SELECT * FROM Orders)
BEGIN
    INSERT INTO Orders VALUES
    (1001, 1, 101, 1, '2024-01-10'),
    (1002, 1, 102, 2, '2024-01-12'),
    (1003, 2, 103, 3, '2024-01-15'),
    (1004, 3, 101, 1, '2024-01-20');
END
GO