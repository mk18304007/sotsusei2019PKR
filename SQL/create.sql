
-- Users�\
	CREATE TABLE Users(
		managementID 	NUMBER(10)�@CONSTRAINT PK_Users_managementID PRIMARY KEY,
		userID 			VARCHAR2(100) CONSTRAINT UQ_NN_Users_userID UNIQUE NOT NULL,
		userName 		VARCHAR2(100) CONSTRAINT NN_Users_userName NOT NULL,
		mailAddress 	VARCHAR2(30) CONSTRAINT UQ_NN_Users_mailAddres UNIQUE NOT NULL,
		password 		VARCHAR2(20) CONSTRAINT NN_Users_password NOT NULL,
		profile 		VARCHAR2(800) DEFAULT '' CONSTRAINT NN_Users_profile NOT NULL,
		profilePicture 	VARCHAR2(1000) DEFAULT '�����摜�p�X' CONSTRAINT NN_Users_profilePicture NOT NULL,
		state 			NUMBER(1) DEFAULT 1 CONSTRAINT NN_Users_state NOT NULL,
		postCount 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_postCount NOT NULL,
		likesCount 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_likesCount NOT NULL,
		follows 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_follows NOT NULL,
		followers 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Users_followers NOT NULL,
		report 			NUMBER(2) DEFAULT 0 CONSTRAINT NN_Users_report NOT NULL,
		registeredDate 	DATE DEFAULT SYSDATE CONSTRAINT NN_Users_registeredDate NOT NULL
	);

-- Post�\
	CREATE TABLE Post(
		postID 			NUMBER(12) CONSTRAINT PK_Post_postID PRIMARY KEY,
		managementID 	NUMBER(10),
		contents 		VARCHAR2(1000) DEFAULT '�G���[�摜' CONSTRAINT NN_Post_contents NOT NULL,
		text 			VARCHAR2(4000) DEFAULT '',
		report 			NUMBER(2) DEFAULT 0 CONSTRAINT NN_Post_report NOT NULL,
		likesCount 		NUMBER(10) DEFAULT 0 CONSTRAINT NN_Post_likesCount NOT NULL,
		CONSTRAINT FK_Post_managementID FOREIGN KEY(managementID) REFERENCES Users(managementID)
	);

-- Reply�\
	CREATE TABLE Reply(
		replyID 	 NUMBER(12) CONSTRAINT PK_Reply_replyID PRIMARY KEY,
		managementID NUMBER(9),
		postID 		 NUMBER(9),
		reply 	 	 VARCHAR2(2000) DEFAULT'' CONSTRAINT NN_Reoly_reply NOT NULL,
		commentID 	 NUMBER(12),
		likesCount 	 NUMBER(10) DEFAULT 0 CONSTRAINT NN_Reply_likesCount NOT NULL,
		state 		 NUMBER(1) DEFAULT 0, --���e�ɑ΂��郊�v���C(0)/���v���C�ɑ΂��郊�v���C(1)
		CONSTRAINT FK_Reply_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Reply_postID 			FOREIGN KEY(postID) 		REFERENCES Post(postID),
		CONSTRAINT SK_Reply_commentID 		FOREIGN KEY(commentID) 		REFERENCES Reply(replyID)
	);

-- Likes�\
	CREATE TABLE Likes(
		likeID			NUMBER(14) CONSTRAINT PK_Likes_likeID PRIMARY KEY,
		managementID 	NUMBER(10),
		postID			NUMBER(12),
		ReplyID 		NUMBER(12),
		state 			NUMBER(1) DEFAULT(0), --���e�ɑ΂���(0)/���v���C�ɑ΂���(1)
		CONSTRAINT FK_Likes_managementID 	FOREIGN KEY(managementID) 	REFERENCES Users(managementID),
		CONSTRAINT FK_Likes_postID 			FOREIGN KEY(postID) 		REFERENCES Post(postID),
		CONSTRAINT FK_Likes_replyID 		FOREIGN KEY(replyID) 		REFERENCES Reply(replyID)
	);


-- Action�\
	CREATE TABLE Action(
		actionID 			NUMBER(15) CONSTRAINT FK_Action_actionID PRIMARY KEY,
		activeManagementID 	NUMBER(15),
		passiveManagementID NUMBER(15),
		state 				NUMBER(1) CONSTRAINT NN_Action_state NOT NULL, --�t�H���[(0)/�u���b�N(1)/������(2)/���v���C(3)/���e(4)/DM(5)
		timeStamp 			DATE DEFAULT SYSDATE CONSTRAINT NN_Action_timeStamp NOT NULL,
		CONSTRAINT FK_Action_activeManagementID  FOREIGN KEY(activeManagementID)  REFERENCES Users(managementID),
		CONSTRAINT FK_Action_passiveManagementID FOREIGN KEY(passiveManagementID) REFERENCES Users(managementID)
	);

-- Directmail�\
	CREATE TABLE Directmail(
		sendManagementID NUMBER(10),
		sentManagementID NUMBER(10),
		talk 			 VARCHAR2(4000),
		contents 		 VARCHAR2(1000),
		CONSTRAINT FK_Directmail_sendManagementID FOREIGN KEY(sendManagementID) REFERENCES Users(managementID),
		CONSTRAINT FK_Directmail_sentManagementID FOREIGN KEY(sentManagementID) REFERENCES Users(managementID)
	);


