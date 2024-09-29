-- 유저 더미 --
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('ssar12', 'zheld8282@', 'ssar@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('cos', '1234', 'cos@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('love', '1234', 'love@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('vivi', '1234', 'vivi@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('cindy', '1234', 'cindy@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('mango', '1234', 'mango@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('kia12', '1234', 'kia12@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('dragon', '1234', 'dragon@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('haha', '1234', 'haha@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('soso', '1234', 'soso@nate.com', now());
INSERT INTO user_tb (username, password, email, created_at)
VALUES ('code5', 'dirn111dnl@', 'codingstory2@gmail.com', now());
-- 유저 더미 끝 --

-- 카테고리 더미 --
INSERT INTO category_tb (category_name, user_id, created_at)
VALUES ('여행', '1', now());
INSERT INTO category_tb (category_name, user_id, created_at)
VALUES ('동물', '1', now());
INSERT INTO category_tb (category_name, user_id, created_at)
VALUES ('동물/여행', '2', now());
INSERT INTO category_tb (category_name, user_id, created_at)
VALUES ('음식', '2', now());
INSERT INTO category_tb (category_name, user_id, created_at)
VALUES ('다이어트', '3', now());
-- 카테고리 더미 끝 --

-- 게시글(포스트) 더미
-- INSERT INTO post_tb (title, content, user_id, category_id, thumbnail_file, created_at)
-- VALUES ('스위스 13박 14일 여행 후기❤', '스위스에서 보낸 13박 14일 여행 후기!! 아름다운 자연 경관과 맛있는 음식들을 즐길 수 있었습니다.', 1, 1, 'swiss.jpg', NOW()),
--        ('여름에 일본 빨리 갔다오기 (자유 여행)', '안녕하세요~ 오늘은 일본 여행에 대한 포스팅을 해보려고 해요!! 이번 여름에 일본을 갔다왔는데, 덥더라구요~~~', 1, 1, 'japan.jpg', NOW()),
--        ('강아지와의 만남', '산책 나갔을 때 보았던 강아지!', 1, 2, 'dog.jpg', NOW()),
--        ('고양이 집사의 하루 (네로와 함께)', '우리 집 고양이 네로와 함께하는 하루 이야기를 들려드립니다.', 1, 2, 'cat.jpg', NOW()),
--
--        ('요리 레시피 - 마르게리타 피자', '간단하지만 맛있는 마르게리타 피자 만들기! 레시피를 공유합니다~ 아이들 점심 식사나 간식으로 좋아요^^', 2, 4, 'pizza.jpg', NOW()),
--        ('맛있는 파스타 만들기', '집에서 직접 만든 맛있는 파스타 레시피를 공유합니다.', 2, 4, 'pasta.jpg', NOW()),
--        ('뉴질랜드 가보신분? (저요! 🙋‍♀️)', '뉴질랜드의 아름다운 바다 풍경을 담은 사진입니다 ^^', 2, 3, 'newZealand.jpg', NOW()),
--
--        ('홈트레이닝 다이어트 루틴', '집에서 운동하는 분들을 위한 효과적인 홈트레이닝 루틴을 소개합니다.', 3, 5, 'exercise.jpg', NOW());
--        ('IT 기술 동향 - 5G 네트워크 발전', '최근 5G 네트워크 기술의 발전 동향과 향후 전망에 대해 정리해보았습니다.', 5, 2, '5g_network.jpg', NOW()),
