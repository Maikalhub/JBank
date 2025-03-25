
-- Триггер для автоматического добавления записи в таблицу Groups после вставки в UserID
CREATE TRIGGER trg_AfterInsert_UserID
ON UserID
AFTER INSERT
AS
BEGIN
    -- Добавление записи в таблицу Groups
    INSERT INTO Groups (ФИО, Статус, НомерГруппы)
    SELECT ФИО, Статус, НомерГруппы
    FROM INSERTED;
END;
GO

-- Триггер для автоматического добавления записи в таблицу Groups после вставки в AdminID
CREATE TRIGGER trg_AfterInsert_AdminID
ON AdminID
AFTER INSERT
AS
BEGIN
    -- Добавление записи в таблицу Groups
    INSERT INTO Groups (ФИО, Статус, НомерГруппы)
    SELECT ФИО, Статус, НомерГруппы
    FROM INSERTED;
END;
GO
