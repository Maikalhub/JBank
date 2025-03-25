CREATE DATABASE Data2
 ON PRIMARY                   
   ( NAME = Data_data,  
     FILENAME = 'D:\БГУИР\2023-2024     (3 курс)\5 семестр\Курсовая работа\k\Data\Data_data.mdf',
     SIZE = 5MB, 
     MAXSIZE = 75MB,
     FILEGROWTH = 3MB ),
 FILEGROUP Secondary
   ( NAME = Data2_data,
     FILENAME = 'D:\БГУИР\2023-2024     (3 курс)\5 семестр\Курсовая работа\k\Data\Data_data2.ndf',
     SIZE = 3MB, 
     MAXSIZE = 50MB,
     FILEGROWTH = 15% ),
   ( NAME = Data3_data,
     FILENAME = 'D:\БГУИР\2023-2024     (3 курс)\5 семестр\Курсовая работа\k\Data\Data_data3.ndf',
     SIZE = 4MB, 
     FILEGROWTH = 4MB )
 LOG ON
   ( NAME = Data_log,
     FILENAME = 'D:\БГУИР\2023-2024     (3 курс)\5 семестр\Курсовая работа\k\Data\Data_log.ldf',
     SIZE = 1MB,
     MAXSIZE = 10MB,
     FILEGROWTH = 20% ),
   ( NAME = Data2_log,
     FILENAME = 'D:\БГУИР\2023-2024     (3 курс)\5 семестр\Курсовая работа\k\Data\Data_log2.ldf',
     SIZE = 512KB,
     MAXSIZE = 15MB,
     FILEGROWTH = 10% )