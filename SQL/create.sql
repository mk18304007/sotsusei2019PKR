
-- Users表
	CREATE TABLE Users(
		managementID 	NUMBER(10) CONSTRAINT PK_Users_managementID PRIMARY KEY,
		userID 			VARCHAR2(100) CONSTRAINT UQ_NN_Users_userID UNIQUE NOT NULL,
		userName 		VARCHAR2(100) CONSTRAINT NN_Users_userName NOT NULL,
		mailAddress 	VARCHAR2(30) CONSTRAINT UQ_NN_Users_mailAddres UNIQUE NOT NULL,
		password 		VARCHAR2(20) CONSTRAINT NN_Users_password NOT NULL,
		profile 		VARCHAR2(800) DEFAULT ' ' CONSTRAINT NN_Users_profile NOT NULL,
		profilePicture 	VARCHAR2(1000) DEFAULT 'default_icon.jpg' CONSTRAINT NN_Users_profilePicture NOT NULL,
		state 			NUMBER(1) DEFAULT 1 CONSTRAINT NN_Users_state NOT NULL,
		postCount 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_postCount NOT NULL,
		likesCount 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_likesCount NOT NULL,
		follows 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_follows NOT NULL,
		followers 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_followers NOT NULL,
		report 			NUMBER(2) DEFAULT 0 CONSTRAINT NN_Users_report NOT NULL,
		registeredDate 	DATE DEFAULT SYSDATE CONSTRAINT NN_Users_registeredDate NOT NULL
	);

-- Post表
	CREATE TABLE Post(
		postID 			NUMBER(12) CONSTRAINT PK_Post_postID PRIMARY KEY,
		managementID 	NUMBER(10),
		contents1 		VARCHAR2(100) CONSTRAINT NN_Post_contents NOT NULL,
		contents2 		VARCHAR2(100),
		contents3 		VARCHAR2(100),
		contents4 		VARCHAR2(100),
		contents5 		VARCHAR2(100),
		contents6 		VARCHAR2(100),
		contents7 		VARCHAR2(100),
		contents8 		VARCHAR2(100),
		contents9		VARCHAR2(100),
		contents10 		VARCHAR2(100),
		text 			VARCHAR2(4000) DEFAULT ' ',
		report 			NUMBER(2) DEFAULT 0 CONSTRAINT NN_Post_report NOT NULL,
		likesCount 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Post_likesCount NOT NULL,
		CONSTRAINT FK_Post_managementID FOREIGN KEY(managementID) REFERENCES Users(managementID),
		timeStamp 		DATE DEFAULT SYSDATE CONSTRAINT NN_Post_timeStamp NOT NULL
	);

-- Reply表
	CREATE TABLE Reply(
		replyID 	 NUMBER(12) CONSTRAINT PK_Reply_replyID PRIMARY KEY,
		managementID NUMBER(9),
		postID 		 NUMBER(9),
		reply 	 	 VARCHAR2(2000) DEFAULT'' CONSTRAINT NN_Reoly_reply NOT NULL,
		commentID 	 NUMBER(12),
		likesCount 	 NUMBER(10) DEFAULT 0 CONSTRAINT NN_Reply_likesCount NOT NULL,
		state 		 NUMBER(1) DEFAULT 0, --投稿に対するリプライ(0)/リプライに対するリプライ(1)
		CONSTRAINT FK_Reply_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Reply_postID 			FOREIGN KEY(postID) 		REFERENCES Post(postID),
		CONSTRAINT SK_Reply_commentID 		FOREIGN KEY(commentID) 		REFERENCES Reply(replyID)
	);
-- Likes表
	CREATE TABLE Likes(
		likeID			NUMBER(14) CONSTRAINT PK_Likes_likeID PRIMARY KEY,
		managementID 	NUMBER(10),
		postID			NUMBER(12),
		ReplyID 		NUMBER(12),
		state 			NUMBER(1) DEFAULT(0), --投稿に対する(0)/リプライに対する(1)
		CONSTRAINT FK_Likes_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Likes_postID 			FOREIGN KEY(postID) 		REFERENCES Post(postID),
		CONSTRAINT FK_Likes_replyID 		FOREIGN KEY(replyID) 		REFERENCES Reply(replyID)
	);
-- Action表
	CREATE TABLE Action(
		actionID 			NUMBER(15) CONSTRAINT FK_Action_actionID PRIMARY KEY,
		activeManagementID 	NUMBER(15),
		passiveManagementID NUMBER(15),
		state 				NUMBER(1) CONSTRAINT NN_Action_state NOT NULL, --フォロー(0)/ブロック(1)/いいね(2)/リプライ(3)/投稿(4)/DM(5)
		timeStamp 			DATE DEFAULT SYSDATE CONSTRAINT NN_Action_timeStamp NOT NULL,
		CONSTRAINT FK_Action_activeManagementID  FOREIGN KEY(activeManagementID)  REFERENCES Users(managementID),
		CONSTRAINT FK_Action_passiveManagementID FOREIGN KEY(passiveManagementID) REFERENCES Users(managementID)
	);
-- Directmail表
	CREATE TABLE Directmail(
		sendManagementID NUMBER(10),
		sentManagementID NUMBER(10),
		talk 			 VARCHAR2(4000),
		contents 		 VARCHAR2(1000),
		state			 NUMBER(1) DEFAULT 0,
		CONSTRAINT FK_Directmail_sendManagementID FOREIGN KEY(sendManagementID) REFERENCES Users(managementID),
		CONSTRAINT FK_Directmail_sentManagementID FOREIGN KEY(sentManagementID) REFERENCES Users(managementID)
	);
