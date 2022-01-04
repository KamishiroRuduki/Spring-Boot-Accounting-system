# Spring-Boot-Accounting-system



-----------------------------------------------------------以下為SQL Server指令碼---------------------------------------------------------------------------
USE [master]
GO
/****** Object:  Database [AccountingNote]    Script Date: 2022/01/04 21:54:58 ******/
CREATE DATABASE [AccountingNote]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'AccountingDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\AccountingDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'AccountingDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\AccountingDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [AccountingNote] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [AccountingNote].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [AccountingNote] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [AccountingNote] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [AccountingNote] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [AccountingNote] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [AccountingNote] SET ARITHABORT OFF 
GO
ALTER DATABASE [AccountingNote] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [AccountingNote] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [AccountingNote] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [AccountingNote] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [AccountingNote] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [AccountingNote] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [AccountingNote] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [AccountingNote] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [AccountingNote] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [AccountingNote] SET  DISABLE_BROKER 
GO
ALTER DATABASE [AccountingNote] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [AccountingNote] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [AccountingNote] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [AccountingNote] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [AccountingNote] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [AccountingNote] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [AccountingNote] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [AccountingNote] SET RECOVERY FULL 
GO
ALTER DATABASE [AccountingNote] SET  MULTI_USER 
GO
ALTER DATABASE [AccountingNote] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [AccountingNote] SET DB_CHAINING OFF 
GO
ALTER DATABASE [AccountingNote] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [AccountingNote] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [AccountingNote] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [AccountingNote] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'AccountingNote', N'ON'
GO
ALTER DATABASE [AccountingNote] SET QUERY_STORE = OFF
GO
USE [AccountingNote]
GO
USE [AccountingNote]
GO
/****** Object:  Sequence [dbo].[hibernate_sequence]    Script Date: 2022/01/04 21:54:58 ******/
CREATE SEQUENCE [dbo].[hibernate_sequence] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
GO
/****** Object:  Table [dbo].[accounting_note]    Script Date: 2022/01/04 21:54:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[accounting_note](
	[accid] [int] IDENTITY(1,1) NOT NULL,
	[act_type] [int] NOT NULL,
	[amount] [int] NOT NULL,
	[body] [nvarchar](500) NULL,
	[caption] [nvarchar](100) NULL,
	[categoryid] [uniqueidentifier] NULL,
	[create_date] [datetime] NOT NULL,
	[userid] [uniqueidentifier] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[accid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[AccountingNote]    Script Date: 2022/01/04 21:54:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountingNote](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [uniqueidentifier] NOT NULL,
	[Caption] [nvarchar](100) NULL,
	[Amount] [int] NOT NULL,
	[ActType] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
	[Body] [nvarchar](500) NULL,
 CONSTRAINT [PK_AccountingNote] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 2022/01/04 21:54:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[categoryid] [uniqueidentifier] NOT NULL,
	[body] [nvarchar](500) NULL,
	[caption] [nvarchar](100) NOT NULL,
	[create_date] [datetime] NOT NULL,
	[userid] [uniqueidentifier] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[categoryid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_info]    Script Date: 2022/01/04 21:54:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_info](
	[id] [uniqueidentifier] NOT NULL,
	[account] [varchar](50) NOT NULL,
	[create_date] [datetime] NOT NULL,
	[email] [nvarchar](100) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[pwd] [varchar](50) NOT NULL,
	[user_level] [int] NOT NULL,
	[edit_date] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserInfo]    Script Date: 2022/01/04 21:54:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserInfo](
	[ID] [uniqueidentifier] NOT NULL,
	[Account] [varchar](50) NOT NULL,
	[PWD] [varchar](50) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](100) NOT NULL,
	[UserLevel] [int] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_UserInfo] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[accounting_note] ON 
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (1, 1, 111, NULL, NULL, N'46190f6b-9463-4df5-8399-662a08ac61d7', CAST(N'2021-08-03T15:57:29.487' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (3, 0, 222, NULL, NULL, N'036f8c69-96b9-4ba4-a5ff-6e01ce67f17d', CAST(N'2021-08-05T22:46:35.930' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (4, 1, 500, N'shikinatsume', N'shikinatsume', N'30d4cd6b-fbf0-48b0-aa52-228205d4fddd', CAST(N'2021-08-05T22:46:41.880' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (7, 1, 100, NULL, NULL, NULL, CAST(N'2021-12-14T12:20:16.733' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (8, 1, 540, N'kasuganosora', N'ariharananami', N'96f9171c-2e8f-4312-8201-fac2598216b5', CAST(N'2021-12-20T12:20:16.733' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (10, 0, 100, N'test', N'test', N'46190f6b-9463-4df5-8399-662a08ac61d7', CAST(N'2021-12-17T14:51:59.270' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (12, 0, 1, N'', N'test2', NULL, CAST(N'2021-12-17T15:13:47.777' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (15, 1, 1, N'', N'test3', NULL, CAST(N'2021-12-17T15:19:07.013' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[accounting_note] ([accid], [act_type], [amount], [body], [caption], [categoryid], [create_date], [userid]) VALUES (18, 1, 1, N'', N'', NULL, CAST(N'2021-12-17T16:05:31.103' AS DateTime), N'ee632f85-6c9b-4108-9c8b-d10f762094bf')
GO
SET IDENTITY_INSERT [dbo].[accounting_note] OFF
GO
SET IDENTITY_INSERT [dbo].[AccountingNote] ON 
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (3, N'ee632f85-6c9b-4108-9c8b-d10f762094bf', N'Caption', 99999, 0, CAST(N'2021-08-02T00:24:06.653' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (4, N'f662b62f-da06-4084-a432-53b05d26e653', N'Captiontest', 6666, 1, CAST(N'2021-08-02T22:46:31.960' AS DateTime), N'test')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (5, N'f67472d7-3dd3-44a8-9a5d-02da57d027c3', N'Caption', 24444, 1, CAST(N'2021-08-03T15:57:29.487' AS DateTime), N'')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (10, N'f662b62f-da06-4084-a432-53b05d26e653', N'1', 10000, 0, CAST(N'2021-08-03T23:26:28.867' AS DateTime), N'1')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (11, N'f662b62f-da06-4084-a432-53b05d26e653', N'2', 10000, 0, CAST(N'2021-08-03T23:26:47.367' AS DateTime), N'2')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (12, N'f662b62f-da06-4084-a432-53b05d26e653', N'3', 100000, 1, CAST(N'2021-08-03T23:27:03.733' AS DateTime), N'3')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (13, N'f662b62f-da06-4084-a432-53b05d26e653', N'Caption', 2333, 0, CAST(N'2021-08-05T22:45:41.257' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (14, N'f662b62f-da06-4084-a432-53b05d26e653', N'11155', 444, 1, CAST(N'2021-08-05T22:45:48.737' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (15, N'f662b62f-da06-4084-a432-53b05d26e653', N'Caption', 22, 0, CAST(N'2021-08-05T22:45:53.820' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (16, N'f662b62f-da06-4084-a432-53b05d26e653', N'Caption', 4444, 1, CAST(N'2021-08-05T22:46:00.767' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (17, N'f662b62f-da06-4084-a432-53b05d26e653', N'Caption', 55666, 0, CAST(N'2021-08-05T22:46:30.730' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (18, N'f662b62f-da06-4084-a432-53b05d26e653', N'Caption', 4444, 0, CAST(N'2021-08-05T22:46:35.930' AS DateTime), N'Desc')
GO
INSERT [dbo].[AccountingNote] ([ID], [UserID], [Caption], [Amount], [ActType], [CreateDate], [Body]) VALUES (19, N'f662b62f-da06-4084-a432-53b05d26e653', N'Caption', 55555, 0, CAST(N'2021-08-05T22:46:41.880' AS DateTime), N'Desc')
GO
SET IDENTITY_INSERT [dbo].[AccountingNote] OFF
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'6421f9aa-018a-4ad2-b3b6-02b0d7599346', N'', N'123456789', CAST(N'2021-12-15T16:41:01.323' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'30d4cd6b-fbf0-48b0-aa52-228205d4fddd', N'body3', N'四季ナツメ', CAST(N'2021-12-13T12:20:13.637' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'6a065ed5-a992-4a6e-9cbe-31cb76a33379', N'123', N'caption3', CAST(N'2022-01-03T12:10:27.297' AS DateTime), N'ee632f85-6c9b-4108-9c8b-d10f762094bf')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'ded9b7f4-7557-463a-b9f8-4d49994258bb', N'test123444', N'123456', CAST(N'2021-12-13T21:57:19.867' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'46190f6b-9463-4df5-8399-662a08ac61d7', N'body1', N'caption1', CAST(N'2021-12-09T12:28:35.770' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'e07a6cd4-319f-49ca-b58e-6a61b08dddc8', N'', N'caption333', CAST(N'2021-12-14T12:20:16.733' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'036f8c69-96b9-4ba4-a5ff-6e01ce67f17d', N'body2', N'caption2', CAST(N'2021-12-09T12:29:00.140' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'3faaa2e6-101a-4540-ba0d-ae7b0b42321f', N'', N'在原七海', CAST(N'2021-12-14T20:20:38.617' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'c362330d-6c3a-4967-bc80-b1e9aeee43ed', N'', N'caption3', CAST(N'2021-12-14T18:33:50.297' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'27fbaeaa-2095-408e-b71c-cab6cf22fcdc', N'12', N'朱鷺坂七緒', CAST(N'2021-12-15T16:38:54.187' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'd7dd866f-0110-42db-8151-e00507951d35', N'test444', N'test44', CAST(N'2021-12-13T16:29:49.380' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'89943817-f927-4a19-bc3c-e16d50864100', N'test33', N'test33', CAST(N'2021-12-13T16:36:59.047' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[category] ([categoryid], [body], [caption], [create_date], [userid]) VALUES (N'96f9171c-2e8f-4312-8201-fac2598216b5', N'妹最高！', N'在原七海、春日野穹', CAST(N'2021-12-14T18:58:33.230' AS DateTime), N'f662b62f-da06-4084-a432-53b05d26e653')
GO
INSERT [dbo].[user_info] ([id], [account], [create_date], [email], [name], [pwd], [user_level], [edit_date]) VALUES (N'f662b62f-da06-4084-a432-53b05d26e653', N'adminss', CAST(N'2021-08-02T12:00:00.000' AS DateTime), N'admin@gmail.com', N'admin1', N'12345678', 0, CAST(N'2021-12-29T22:09:44.890' AS DateTime))
GO
INSERT [dbo].[user_info] ([id], [account], [create_date], [email], [name], [pwd], [user_level], [edit_date]) VALUES (N'9ea51d3f-fb32-4138-b548-b20bfe606100', N'test', CAST(N'2021-12-29T10:07:44.000' AS DateTime), N'adminTest@gmail.com', N'test1', N'12345678', 0, CAST(N'2021-12-29T22:09:50.937' AS DateTime))
GO
INSERT [dbo].[user_info] ([id], [account], [create_date], [email], [name], [pwd], [user_level], [edit_date]) VALUES (N'ee632f85-6c9b-4108-9c8b-d10f762094bf', N'admin2', CAST(N'2021-08-06T09:15:46.000' AS DateTime), N'Test2@yahoo.com', N'test2', N'12345678', 1, CAST(N'2021-12-29T22:11:58.280' AS DateTime))
GO
INSERT [dbo].[UserInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate]) VALUES (N'f662b62f-da06-4084-a432-53b05d26e653', N'adminss', N'ssr', N'admin', N'admin@gmail.com', 0, CAST(N'2021-08-02T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[UserInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate]) VALUES (N'ee632f85-6c9b-4108-9c8b-d10f762094bf', N'admin2', N'12345', N'Test2', N'Test2@yahoo.com', 1, CAST(N'2021-08-01T00:00:00.000' AS DateTime))
GO
INSERT [dbo].[UserInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate]) VALUES (N'89b363da-be0a-4b64-92ea-dd5c8697df67', N'admintest', N'12345', N'testname', N'testmail@333.com', 1, CAST(N'2021-08-06T09:15:46.240' AS DateTime))
GO
INSERT [dbo].[UserInfo] ([ID], [Account], [PWD], [Name], [Email], [UserLevel], [CreateDate]) VALUES (N'5ac60a39-94a7-491e-97dd-f170cb0bc268', N'admin777', N'88888888', N'testname', N'testmail@333.com', 1, CAST(N'2021-08-03T16:53:08.617' AS DateTime))
GO
ALTER TABLE [dbo].[accounting_note] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[category] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[user_info] ADD  DEFAULT (getdate()) FOR [create_date]
GO
USE [master]
GO
ALTER DATABASE [AccountingNote] SET  READ_WRITE 
GO
