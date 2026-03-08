-- Insert record (Always specify column names)
INSERT INTO Employees (EmpID, EmpName, Salary)
VALUES (1, 'John Doe', 50000);

-- Update record
UPDATE Employees
SET Salary = 55000
WHERE EmpID = 1;

-- View audit logs
SELECT * FROM Audit_Logs;

-- View daily activity reports (only if table exists)
SELECT * FROM Daily_Activity_Reports;