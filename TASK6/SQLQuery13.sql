CREATE TRIGGER trg_employee_audit
ON Employees
AFTER INSERT, UPDATE
AS
BEGIN
    SET NOCOUNT ON;

    -- INSERT Operation
    IF EXISTS (SELECT 1 FROM inserted)
       AND NOT EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO Audit_Logs (TableName, Action, NewData)
        SELECT 
            'Employees',
            'INSERT',
            (SELECT i.* FROM inserted i FOR JSON PATH);
    END

    -- UPDATE Operation
    IF EXISTS (SELECT 1 FROM inserted)
       AND EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO Audit_Logs (TableName, Action, OldData, NewData)
        SELECT 
            'Employees',
            'UPDATE',
            (SELECT d.* FROM deleted d FOR JSON PATH),
            (SELECT i.* FROM inserted i FOR JSON PATH);
    END
END;
GO