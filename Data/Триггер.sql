
-- ������� ��� ��������������� ���������� ������ � ������� Groups ����� ������� � UserID
CREATE TRIGGER trg_AfterInsert_UserID
ON UserID
AFTER INSERT
AS
BEGIN
    -- ���������� ������ � ������� Groups
    INSERT INTO Groups (���, ������, �����������)
    SELECT ���, ������, �����������
    FROM INSERTED;
END;
GO

-- ������� ��� ��������������� ���������� ������ � ������� Groups ����� ������� � AdminID
CREATE TRIGGER trg_AfterInsert_AdminID
ON AdminID
AFTER INSERT
AS
BEGIN
    -- ���������� ������ � ������� Groups
    INSERT INTO Groups (���, ������, �����������)
    SELECT ���, ������, �����������
    FROM INSERTED;
END;
GO
