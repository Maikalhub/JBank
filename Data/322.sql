USE Data2;

-- �������� ������

-- ������� ������������� UserID
CREATE TABLE UserID 
(
   ID           INT IDENTITY(1,1),  -- �������������� ���������� ID
   �����        VARCHAR(50) NOT NULL,
   ������       VARCHAR(50) NOT NULL,
   ���          VARCHAR(50) NOT NULL,
   ���������    VARCHAR(50) NOT NULL,
   ������       VARCHAR(50) NOT NULL CHECK (������ IN ('������', '����� ���������', '���������� ���������', '�������� ���������', '������', '������', '������� � ���������')),
   ��������     MONEY NULL CHECK (�������� > 0),
   ���������    CHAR(3) DEFAULT 'BYR' NULL,
   �����������  VARCHAR(50) NOT NULL,
   CONSTRAINT CIX_UserID UNIQUE (���),
   -- ��������� ��������� ���� �� ����� � ������
   CONSTRAINT PK_UserID_Login_Password PRIMARY KEY (�����, ������)
);

-- ������� ��������������� AdminID
CREATE TABLE AdminID 
(
   ID           INT IDENTITY(1,1),  -- �������������� ���������� ID
   �����        VARCHAR(50) NOT NULL,
   ������       VARCHAR(50) NOT NULL,
   ���          VARCHAR(50) NOT NULL,
   ���������    VARCHAR(50) NOT NULL,
   ������       VARCHAR(50) NOT NULL CHECK (������ IN ('����� ������������', '������������ ������', '�������� ������������ ������', '������', '������', '������� � ��������� �� ������������ ������')),
   ��������     MONEY NULL CHECK (�������� > 0),
   ���������    CHAR(3) DEFAULT 'BYR' NULL,
   �����������  VARCHAR(50) NOT NULL,
   CONSTRAINT CIX_AdminID UNIQUE (���),
   CONSTRAINT PK_AdminID_Login_Password PRIMARY KEY (�����, ������)
);

-- ������� ������������� User_A
CREATE TABLE User_A 
(
   ID     INT IDENTITY(1,1),  -- �������������� ���������� ID
   �����  VARCHAR(50) NOT NULL,
   ������ VARCHAR(50) NOT NULL,
   CONSTRAINT PK_User_A PRIMARY KEY (�����, ������),
   -- ��������� �� ��������� ���� (�����, ������) � ������� UserID
   CONSTRAINT FK_User_A_User_ID FOREIGN KEY (�����, ������) REFERENCES UserID (�����, ������) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ������� ��������������� Admin_A
CREATE TABLE Admin_A 
(
   ID     INT IDENTITY(1,1),  -- �������������� ���������� ID
   �����  VARCHAR(50) NOT NULL,
   ������ VARCHAR(50) NOT NULL,
   CONSTRAINT PK_Admin_A PRIMARY KEY (�����, ������),
   -- ��������� �� ��������� ���� (�����, ������) � ������� AdminID
   CONSTRAINT FK_Admin_A_Admin_ID FOREIGN KEY (�����, ������) REFERENCES AdminID (�����, ������) ON UPDATE CASCADE ON DELETE CASCADE
);

-- ������� �����
CREATE TABLE Groups
(
   ID           INT IDENTITY(1,1),  -- �������������� ���������� ID
   �����������  VARCHAR(50) NOT NULL,
   ���          VARCHAR(50) PRIMARY KEY NOT NULL,  -- ���������� ��� ������� �� ���
   ������������ VARCHAR(50) NOT NULL, -- ������������ ������ (������ �� AdminID)
   CONSTRAINT FK_Groups_UserID FOREIGN KEY (���) REFERENCES UserID (���)
);

-- ������� ����� Works
CREATE TABLE Works
(
   ID            INT IDENTITY(1,1),  -- �������������� ���������� ID
   ���           VARCHAR(50) PRIMARY KEY NOT NULL,
   �����������   VARCHAR(50) NOT NULL,
   ������������  VARCHAR(50) NOT NULL,
   ������        VARCHAR(50) NOT NULL,
   ������������  VARCHAR(50) NOT NULL CHECK (������������ IN ('���������', '� ��������', '�� ���������')),
   ����������    DATETIME NULL,
   ������������  DATETIME NULL,
   CONSTRAINT FK_Works_Groups FOREIGN KEY (���) REFERENCES Groups (���)  -- ���������� ��� �������
);

-- ������� ������� Report
CREATE TABLE Report
(
   ID            INT IDENTITY(1,1),  -- �������������� ���������� ID
   ���           VARCHAR(50) NOT NULL,
   �����������   VARCHAR(50) NOT NULL,
   ������        VARCHAR(50) NOT NULL,
   ����          NVARCHAR(MAX)  -- ����� Data ������ JSON � ���� ������
);

-- ������� ������ Archive
CREATE TABLE Archive
(
   ID            INT IDENTITY(1,1),  -- �������������� ���������� ID
   ���           VARCHAR(50) NOT NULL,
   �����������   VARCHAR(50) NOT NULL,
   ������������  VARCHAR(50) NOT NULL,
   ������        VARCHAR(50) NOT NULL,
   ������������  VARCHAR(50) NOT NULL CHECK (������������ IN ('���������', '� ��������', '�� ���������')),
   ����������    DATETIME NULL,
   ������������  DATETIME NULL,
   CONSTRAINT FK_Archive_Works FOREIGN KEY (���) REFERENCES Works (���)
);
