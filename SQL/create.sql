

-- Users表
	CREATE TABLE Users(
		managementID 	NUMBER(9)　CONSTRAINT PK_managementID PRIMARY KEY,
		userID 			VARCHAR2(100) CONSTRAINT UQ_NN_userID UNIQUE NOT NULL,
		name 			VARCHAR2(100) CONSTRAINT NN_name NOT NULL,
		mailaddress 	VARCHAR2(30) CONSTRAINT UQ_NN_addres UNIQUE NOT NULL,
		password 		VARCHAR2(20) CONSTRAINT NN_users_pass NOT NULL,
		profile 		VARCHAR2(800),
		profilepicture 	VARCHAR2(1000) DEFAULT 'C:\Piccture.jpg',
		release 		NUMBER(1) DEFAULT 0, 
		postcount 		NUMBER(9) DEFAULT 0,
		follows 		NUMBER(9) DEFAULT 0,
		followers 		NUMBER(9) DEFAULT 0,
		likescount 		NUMBER(9) DEFAULT 0,
		state			NUMBER(1) DEFAULT 0,
		registereddate 	DATE DEFAULT SYSDATE
	);

-- Post表
	CREATE TABLE Post(
		postID 			NUMBER(9) CONSTRAINT PK_postID PRIMARY KEY,
		managementID 	NUMBER(9),
		contents 		VARCHAR2(1000) CONSTRAINT NN_picture NOT NULL,
		text 			VARCHAR2(4000) DEFAULT NULL,
		report 			NUMBER(2) DEFAULT 0,
		state			NUMBER(1) DEFAULT 0,
		CONSTRAINT FK_Post_managementID FOREIGN KEY(managementID) REFERENCES Users(managementID)
	);

-- Post_Like表
	CREATE TABLE Post_Like(
		managementID 	NUMBER(9),
		postID 			NUMBER(9),	
		good 			NUMBER(1) DEFAULT(1),
		CONSTRAINT FK_Post_Like_managementID FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Post_Like_postID 		 FOREIGN KEY(postID) 		REFERENCES Post(postID)
	);

-- Coment表
	CREATE TABLE Coment(
		managementID 	NUMBER(9),
		postID 			NUMBER(9),
		comentID 		NUMBER(9) CONSTRAINT UQ_NN_post_comentID UNIQUE,
		coment 			VARCHAR2(2000),
		state			NUMBER(1) DEFAULT 0,
		CONSTRAINT FK_Coment_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Coment_postID 		FOREIGN KEY(postID) 		REFERENCES Post(postID)
	);

-- Coment_Like表
	CREATE TABLE Coment_Like(
		managementID 	NUMBER(9),
		comentID 		NUMBER(9),
		good 			NUMBER(1) DEFAULT(1),
		CONSTRAINT FK_Coment_Like_managementID 		FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Coment_Like_comentID 			FOREIGN KEY(comentID) 		REFERENCES Coment(comentID)
	);

-- Reply表
	CREATE TABLE Reply(
		managementID 	NUMBER(9),
		comentID 		NUMBER(9),
		replyID 		NUMBER(9) CONSTRAINT UQ_NN_coment_replyID UNIQUE,
		reply 			VARCHAR2(2000),
		state			NUMBER(1) DEFAULT 0,
		CONSTRAINT FK_Reply_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Reply_comentID 		FOREIGN KEY(comentID) 		REFERENCES Coment(comentID)
	);
	
-- Reply_Like表
	CREATE TABLE Reply_Like(
		managementID 	NUMBER(9),
		replyID 		NUMBER(9),
		good 			NUMBER(1) DEFAULT(1),
		CONSTRAINT FK_Reply_Like_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Reply_Like_replyID 		FOREIGN KEY(replyID) 		REFERENCES Reply(replyID)		
	);

-- Follow表
	CREATE TABLE Follow(
		followID 				NUMBER(9) CONSTRAINT PK_followID PRIMARY KEY,
		followerManagementID 	NUMBER(9),
		followersManagementID 	NUMBER(9),
		CONSTRAINT FK_Follow_followerID  FOREIGN KEY(followerManagementID)  REFERENCES Users(managementID),
		CONSTRAINT FK_Follow_followersID FOREIGN KEY(followersManagementID) REFERENCES Users(managementID)
	);

-- Directmail表
	CREATE TABLE Directmail(
		sendManagementID NUMBER(9),
		sentManagementID NUMBER(9),
		talk 			 VARCHAR2(4000),
		contents 		 VARCHAR2(1000),
		state			 NUMBER(1) DEFAULT 0,
		CONSTRAINT FK_Directmail_sendManagementID FOREIGN KEY(sendManagementID) REFERENCES Users(managementID),
		CONSTRAINT FK_Directmail_sentManagementID FOREIGN KEY(sentManagementID) REFERENCES Users(managementID)
	);

-- Log表
	CREATE TABLE Log(
		logID 					NUMBER(9) CONSTRAINT PK_logID PRIMARY KEY,
		activeManagementID　	NUMBER(9),
		passiveManagementID 	NUMBER(9),
		--アクション(フォロー(0)orいいね(1)orコメント(2)or投稿(3))
		action 					NUMBER(1) CONSTRAINT NN_action NOT NULL,
		time 					DATE DEFAULT SYSDATE,
		CONSTRAINT FK_Log_activeManagementID  FOREIGN KEY(activeManagementID)  REFERENCES Users(managementID),
		CONSTRAINT FK_Log_passiveManagementID FOREIGN KEY(passiveManagementID) REFERENCES Users(managementID)
	);


