# Spring-Boot-Accounting-system

# SQL Server指令碼請參考檔案AccountingNote.sql

# 刪除LOG會保存在當前專案的Accounting-system資料夾內

一般使用者
INSERT [dbo].[user_info] ([id], [account], [create_date], [email], [name], [pwd], [user_level], [edit_date]) VALUES (N'f662b62f-da06-4084-a432-53b05d26e653', N'adminss', CAST(N'2021-08-02T12:00:00.000' AS DateTime), N'admin@gmail.com', N'admin1', N'12345678', 0, CAST(N'2021-12-29T22:09:44.890' AS DateTime))

管理員
INSERT [dbo].[user_info] ([id], [account], [create_date], [email], [name], [pwd], [user_level], [edit_date]) VALUES (N'ee632f85-6c9b-4108-9c8b-d10f762094bf', N'admin2', CAST(N'2021-08-06T09:15:46.000' AS DateTime), N'Test2@yahoo.com', N'test2', N'12345678', 1, CAST(N'2021-12-29T22:11:58.280' AS DateTime))

