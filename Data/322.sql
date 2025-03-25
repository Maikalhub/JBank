USE Data2;

-- Создание таблиц

-- Таблица пользователей UserID
CREATE TABLE UserID 
(
   ID           INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   Логин        VARCHAR(50) NOT NULL,
   Пароль       VARCHAR(50) NOT NULL,
   ФИО          VARCHAR(50) NOT NULL,
   Должность    VARCHAR(50) NOT NULL,
   Статус       VARCHAR(50) NOT NULL CHECK (Статус IN ('Стажер', 'Новый сотрудник', 'Неактивный сотрудник', 'Активный сотрудник', 'Отпуск', 'Уволен', 'Повышен в должности')),
   Зарплата     MONEY NULL CHECK (Зарплата > 0),
   КодВалюты    CHAR(3) DEFAULT 'BYR' NULL,
   НомерГруппы  VARCHAR(50) NOT NULL,
   CONSTRAINT CIX_UserID UNIQUE (ФИО),
   -- Составной первичный ключ на Логин и Пароль
   CONSTRAINT PK_UserID_Login_Password PRIMARY KEY (Логин, Пароль)
);

-- Таблица администраторов AdminID
CREATE TABLE AdminID 
(
   ID           INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   Логин        VARCHAR(50) NOT NULL,
   Пароль       VARCHAR(50) NOT NULL,
   ФИО          VARCHAR(50) NOT NULL,
   Должность    VARCHAR(50) NOT NULL,
   Статус       VARCHAR(50) NOT NULL CHECK (Статус IN ('Новый руководитель', 'Руководитель группы', 'Активный руководитель группы', 'Отпуск', 'Уволен', 'Повышен в должности до руководителя группы')),
   Зарплата     MONEY NULL CHECK (Зарплата > 0),
   КодВалюты    CHAR(3) DEFAULT 'BYR' NULL,
   НомерГруппы  VARCHAR(50) NOT NULL,
   CONSTRAINT CIX_AdminID UNIQUE (ФИО),
   CONSTRAINT PK_AdminID_Login_Password PRIMARY KEY (Логин, Пароль)
);

-- Таблица пользователей User_A
CREATE TABLE User_A 
(
   ID     INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   Логин  VARCHAR(50) NOT NULL,
   Пароль VARCHAR(50) NOT NULL,
   CONSTRAINT PK_User_A PRIMARY KEY (Логин, Пароль),
   -- Ссылаемся на составной ключ (Логин, Пароль) в таблице UserID
   CONSTRAINT FK_User_A_User_ID FOREIGN KEY (Логин, Пароль) REFERENCES UserID (Логин, Пароль) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Таблица администраторов Admin_A
CREATE TABLE Admin_A 
(
   ID     INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   Логин  VARCHAR(50) NOT NULL,
   Пароль VARCHAR(50) NOT NULL,
   CONSTRAINT PK_Admin_A PRIMARY KEY (Логин, Пароль),
   -- Ссылаемся на составной ключ (Логин, Пароль) в таблице AdminID
   CONSTRAINT FK_Admin_A_Admin_ID FOREIGN KEY (Логин, Пароль) REFERENCES AdminID (Логин, Пароль) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Таблица групп
CREATE TABLE Groups
(
   ID           INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   НомерГруппы  VARCHAR(50) NOT NULL,
   ФИО          VARCHAR(50) PRIMARY KEY NOT NULL,  -- Исправлено имя столбца на ФИО
   Руководитель VARCHAR(50) NOT NULL, -- Руководитель группы (ссылка на AdminID)
   CONSTRAINT FK_Groups_UserID FOREIGN KEY (ФИО) REFERENCES UserID (ФИО)
);

-- Таблица работ Works
CREATE TABLE Works
(
   ID            INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   ФИО           VARCHAR(50) PRIMARY KEY NOT NULL,
   НомерГруппы   VARCHAR(50) NOT NULL,
   Руководитель  VARCHAR(50) NOT NULL,
   Работа        VARCHAR(50) NOT NULL,
   СтатусРаботы  VARCHAR(50) NOT NULL CHECK (СтатусРаботы IN ('выполнено', 'в процессе', 'не выполнено')),
   ДатаПриема    DATETIME NULL,
   ДатаПроверки  DATETIME NULL,
   CONSTRAINT FK_Works_Groups FOREIGN KEY (ФИО) REFERENCES Groups (ФИО)  -- Исправлено имя столбца
);

-- Таблица отчетов Report
CREATE TABLE Report
(
   ID            INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   ФИО           VARCHAR(50) NOT NULL,
   НомерГруппы   VARCHAR(50) NOT NULL,
   Работа        VARCHAR(50) NOT NULL,
   Файл          NVARCHAR(MAX)  -- Здесь Data хранит JSON в виде строки
);

-- Таблица архива Archive
CREATE TABLE Archive
(
   ID            INT IDENTITY(1,1),  -- Автоматическое увеличение ID
   ФИО           VARCHAR(50) NOT NULL,
   НомерГруппы   VARCHAR(50) NOT NULL,
   Руководитель  VARCHAR(50) NOT NULL,
   Работа        VARCHAR(50) NOT NULL,
   СтатусРаботы  VARCHAR(50) NOT NULL CHECK (СтатусРаботы IN ('выполнено', 'в процессе', 'не выполнено')),
   ДатаПриема    DATETIME NULL,
   ДатаПроверки  DATETIME NULL,
   CONSTRAINT FK_Archive_Works FOREIGN KEY (ФИО) REFERENCES Works (ФИО)
);
