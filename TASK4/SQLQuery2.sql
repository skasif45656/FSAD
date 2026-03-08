IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'OrderManagementDB')
BEGIN
    CREATE DATABASE OrderManagementDB;
END
GO