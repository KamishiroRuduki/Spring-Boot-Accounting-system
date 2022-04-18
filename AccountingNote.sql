USE [AccountingNote]
GO
/****** Object:  Table [dbo].[accounting_note]    Script Date: 2022/01/04 22:14:12 ******/
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
/****** Object:  Table [dbo].[category]    Script Date: 2022/01/04 22:14:12 ******/
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
/****** Object:  Table [dbo].[user_info]    Script Date: 2022/01/04 22:14:12 ******/
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
ALTER TABLE [dbo].[accounting_note] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[category] ADD  DEFAULT (getdate()) FOR [create_date]
GO
ALTER TABLE [dbo].[user_info] ADD  DEFAULT (getdate()) FOR [create_date]
GO
