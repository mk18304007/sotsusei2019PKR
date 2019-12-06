-- Users表のテストINSERT    
INSERT INTO Users(managementID,userID,name,mailaddress,password,profile)VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),'Imamura','いまむら','si18304004@ga.tera-house.ac.jp','z5zCZQ1B','プロフィール文テスト');
INSERT INTO Users(managementID,userID,name,mailaddress,password,profile)VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),'Gorilla','ゴリラゴリラ','gorillaComCom@id.apple.com','a1a1a1','☆');
INSERT INTO Users(managementID,userID,name,mailaddress,password,profile)VALUES((SELECT coalesce(MAX(managementID),0)+1 FROM Users),'Orange','ｵﾚﾝｼﾞ','Orangerenge@ezweb.ne.jp','Orange','^^^^^^^^^');
-- Post表のテストINSERT
INSERT INTO Post(postID,managementID,contents,text)VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),'1','C:\post_picturs\aa.png','初投稿');
INSERT INTO Post(postID,managementID,contents,text)VALUES((SELECT coalesce(MAX(postID),0)+1 FROM Post),'2','C:\post_picturs\bbbbb.png','うほほ');
-- Post_Like表のテストINSERT
INSERT INTO Post_Like(managementID,postID)VALUES('2','1');
INSERT INTO Post_Like(managementID,postID)VALUES('1','2');
INSERT INTO Post_Like(managementID,postID)VALUES('3','1');
-- Coment表のテストINSERT
INSERT INTO Coment(managementID,postID,comentID,coment)VALUES('2','1',(SELECT coalesce(MAX(comentID),0)+1 FROM Coment),'TestComent');
INSERT INTO Coment(managementID,postID,comentID,coment)VALUES('1','2',(SELECT coalesce(MAX(comentID),0)+1 FROM Coment),'COMENTCOMENT');
-- Coment_Like表のテストINSERT
INSERT INTO Coment_Like(managementID,comentID)VALUES('1','1');
INSERT INTO Coment_Like(managementID,comentID)VALUES('3','2');
-- Ripry表のテストINSERT
INSERT INTO Ripry(managementID,comentID,ripryID,ripry) VALUES('3','1',(SELECT coalesce(MAX(ripryID),0)+1 FROM Ripry),'Hello');
INSERT INTO Ripry(managementID,comentID,ripryID,ripry) VALUES('2','2',(SELECT coalesce(MAX(ripryID),0)+1 FROM Ripry),'シェイシェイ');
INSERT INTO Ripry(managementID,ripryID,ripry) VALUES('1',(SELECT coalesce(MAX(ripryID),0)+1 FROM Ripry),'GoodMorning');
-- Ripry_Like表のテストINSERT
INSERT INTO Ripry_Like(managementID,ripryID) VALUES('1','1');
INSERT INTO Ripry_Like(managementID,ripryID) VALUES('1','3');
-- Follow表のテストINSERT
INSERT INTO Follow(followID,follower_managementID,followers_managementID)VALUES((SELECT coalesce(MAX(followID),0)+1 FROM Follow),'1','2');
INSERT INTO Follow(followID,follower_managementID,followers_managementID)VALUES((SELECT coalesce(MAX(followID),0)+1 FROM Follow),'1','3');
INSERT INTO Follow(followID,follower_managementID,followers_managementID)VALUES((SELECT coalesce(MAX(followID),0)+1 FROM Follow),'2','3');
-- Directmail表のテストINSERT
INSERT INTO Directmail(send_managementID,sent_managementID,talk)VALUES('1','2','こんにちわ');
INSERT INTO Directmail(send_managementID,sent_managementID,contents)VALUES('2','3','C:\Directmail\contents\ウホホ.jpg');
INSERT INTO Directmail(send_managementID,sent_managementID,talk,contents)VALUES('3','2','Hello','C:\Dricmail\contents\HelloWord.hello');