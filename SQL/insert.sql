-- Users�\�̃e�X�gINSERT    
INSERT INTO Users(managementID,userID,name,mailaddress,password,profile)VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),'Imamura','���܂ނ�','si18304004@ga.tera-house.ac.jp','z5zCZQ1B','�v���t�B�[�����e�X�g');
INSERT INTO Users(managementID,userID,name,mailaddress,password,profile)VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),'Gorilla','�S�����S����','gorillaComCom@id.apple.com','a1a1a1','��');
INSERT INTO Users(managementID,userID,name,mailaddress,password,profile)VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),'Orange','��ݼ�','Orangerenge@ezweb.ne.jp','Orange','^^^^^^^^^');
-- Post�\�̃e�X�gINSERT
INSERT INTO Post(postID,managementID,contents,text)VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),'1','C:\post_picturs\aa.png','�����e');
INSERT INTO Post(postID,managementID,contents,text)VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),'2','C:\post_picturs\bbbbb.png','���ق�');
-- Post_Like�\�̃e�X�gINSERT
INSERT INTO Post_Like(managementID,postID)VALUES('2','1');
INSERT INTO Post_Like(managementID,postID)VALUES('1','2');
INSERT INTO Post_Like(managementID,postID)VALUES('3','1');
-- Coment�\�̃e�X�gINSERT
INSERT INTO Coment(managementID,postID,comentID,coment)VALUES('2','1',(SELECT coalesce(MAX(comentID),0)+1 FROM Coment),'TestComent');
INSERT INTO Coment(managementID,postID,comentID,coment)VALUES('1','2',(SELECT coalesce(MAX(comentID),0)+1 FROM Coment),'COMENTCOMENT');
-- Coment_Like�\�̃e�X�gINSERT
INSERT INTO Coment_Like(managementID,comentID)VALUES('1','1');
INSERT INTO Coment_Like(managementID,comentID)VALUES('3','2');
-- Ripry�\�̃e�X�gINSERT
INSERT INTO Ripry(managementID,comentID,ripryID,ripry) VALUES('3','1',(SELECT coalesce(MAX(ripryID),0)+1 FROM Ripry),'Hello');
INSERT INTO Ripry(managementID,comentID,ripryID,ripry) VALUES('2','2',(SELECT coalesce(MAX(ripryID),0)+1 FROM Ripry),'�V�F�C�V�F�C');
INSERT INTO Ripry(managementID,ripryID,ripry) VALUES('1',(SELECT coalesce(MAX(ripryID),0)+1 FROM Ripry),'GoodMorning');
-- Ripry_Like�\�̃e�X�gINSERT
INSERT INTO Ripry_Like(managementID,ripryID) VALUES('1','1');
INSERT INTO Ripry_Like(managementID,ripryID) VALUES('1','3');
-- Follow�\�̃e�X�gINSERT
INSERT INTO Follow(followID,follower_managementID,followers_managementID)VALUES((SELECT coalesce(MAX(followID),0)+1 FROM Follow),'1','2');
INSERT INTO Follow(followID,follower_managementID,followers_managementID)VALUES((SELECT coalesce(MAX(followID),0)+1 FROM Follow),'1','3');
INSERT INTO Follow(followID,follower_managementID,followers_managementID)VALUES((SELECT coalesce(MAX(followID),0)+1 FROM Follow),'2','3');
-- Directmail�\�̃e�X�gINSERT
INSERT INTO Directmail(send_managementID,sent_managementID,talk)VALUES('1','2','����ɂ���');
INSERT INTO Directmail(send_managementID,sent_managementID,contents)VALUES('2','3','C:\Directmail\contents\�E�z�z.jpg');
INSERT INTO Directmail(send_managementID,sent_managementID,talk,contents)VALUES('3','2','Hello','C:\Dricmail\contents\HelloWord.hello');