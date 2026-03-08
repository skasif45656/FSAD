USE PaymentDB; 
CREATE TABLE Accounts ( 
AccountId INT IDENTITY(1,1) PRIMARY KEY, 
AccountName VARCHAR(50) NOT NULL, 
AccountType VARCHAR(20) NOT NULL CHECK (AccountType IN 
('USER','MERCHANT')), 
Balance DECIMAL(12,2) NOT NULL CHECK (Balance >= 0) 
); 