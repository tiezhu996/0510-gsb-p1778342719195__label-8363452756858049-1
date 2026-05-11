SET NAMES utf8mb4;
-- 延边朝鲜族自治州介绍网站 - 初始数据

-- 轮播图数据
INSERT INTO carousel (title, image_url, link_url, sort_order, status) VALUES
('长白山天池美景', 'https://aigc-image.bj.bcebos.com/miaobi/5mao/b%2755yL5aSp5rGg6ZyA6KaB6L%2BQ5rCUXzE3MjU2MDgxODEuODczNjU2Nw%3D%3D%27/0.png', '/scenic/1', 1, 1),
('延边朝鲜族民俗文化', 'https://img1.baidu.com/it/u=1254094040,179712417&fm=253&fmt=auto&app=120&f=JPEG?w=1187&h=800', '/introduction', 2, 1),
('延吉市城市风光', 'https://www.yanjinews.com/uploadfile/2025/0515/20250515040802453.jpg', '/scenic/2', 3, 1),
('延边特色美食', 'https://gips2.baidu.com/it/u=3005320689,213746200&fm=3074&app=3074&f=JPEG?w=1920&h=1890&type=normal&func=', '/food', 4, 1),
('图们江畔风光', 'https://qcloud.dpfile.com/pc/U_ppVKt0GagUiDwNcpaj4VnrMjXMfeFkxSv0mSH5KVU0uBaAdUJKEuvovhEncoO8.jpg', '/scenic/3', 5, 1);

-- 新闻数据
INSERT INTO news (title, summary, content, cover_image, category, view_count, is_hot) VALUES
('延边州入选国家文化旅游示范区', '延边朝鲜族自治州凭借其独特的民族文化和自然风光，成功入选国家文化旅游示范区。', '延边朝鲜族自治州位于吉林省东部，以其独特的朝鲜族文化、壮美的长白山风光和丰富的边境旅游资源而闻名。近日，文化和旅游部公布了新一批国家文化旅游示范区名单，延边州成功入选。这标志着延边州在文化旅游发展方面取得了重要成就。', 'https://img1.baidu.com/it/u=1996708542,4005314190&fm=253&app=138&f=JPEG?w=1067&h=800', '文化', 1520, 1),
('长白山冬季旅游火爆开启', '随着冬季的到来，长白山迎来了滑雪旅游的黄金季节，游客络绎不绝。', '长白山作为延边州的标志性景点，每年冬季都会吸引大量滑雪爱好者。今年，长白山滑雪场进行了全面升级，新增了多条雪道和现代化设施，为游客提供更好的滑雪体验。', 'https://aigc-image.bj.bcebos.com/miaobi/5mao/b%2755yL5aSp5rGg6ZyA6KaB6L%2BQ5rCUXzE3MjU2MDgxODEuODczNjU2Nw%3D%3D%27/0.png', '旅游', 980, 1),
('延吉市举办朝鲜族传统文化节', '一年一度的朝鲜族传统文化节在延吉市隆重举行，展示了丰富多彩的民族文化。', '文化节期间，举办了朝鲜族传统舞蹈表演、民族服饰展示、传统美食品鉴等活动，吸引了数万名游客参与。', 'https://img1.baidu.com/it/u=1254094040,179712417&fm=253&fmt=auto&app=120&f=JPEG?w=1187&h=800', '文化', 750, 0);

-- 家乡简介数据
INSERT INTO introduction (section_name, content, images, sort_order) VALUES
('历史沿革', '延边朝鲜族自治州成立于1952年9月3日，是中国最大的朝鲜族聚居地。延边地区历史悠久，早在新石器时代就有人类在此繁衍生息。清朝时期，大量朝鲜族人迁入此地，形成了独特的民族文化。', '["https://b0.bdstatic.com/89b6ce7c2513dce79802d1bf7049e081.jpg"]', 1),
('地理环境', '延边州位于吉林省东部，东与俄罗斯接壤，南与朝鲜以图们江为界。全州总面积4.33万平方公里，地处长白山区，地势由西南向东北倾斜。境内有长白山、图们江等著名自然景观。', '["https://q3.itc.cn/q_70/images03/20241113/24ab053f184442b2b23ece03d13740f4.png"]', 2),
('民族文化', '延边是朝鲜族文化的重要传承地，保留了丰富的民族传统。朝鲜族能歌善舞，传统节日有春节、清明节、端午节、中秋节等。民族乐器有长鼓、伽倻琴等，民族舞蹈有象帽舞、农乐舞等。', '["https://q5.itc.cn/q_70/images03/20250621/c79d2258931f4c0287bed773632356e9.jpeg"]', 3);

-- 风景名胜数据（部分）
INSERT INTO scenic_spot (name, description, detail_content, cover_image, images, location, ticket_price, rating) VALUES
('长白山天池', '中国最大的火山湖，海拔2189.1米，是中朝两国的界湖。', '长白山天池位于长白山主峰火山锥体的顶部，是一座休眠火山，也是松花江、图们江、鸭绿江的发源地。天池四周奇峰林立，池水深幽清澈，是长白山最著名的景观。', 'https://aigc-image.bj.bcebos.com/miaobi/5mao/b%2755yL5aSp5rGg6ZyA6KaB6L%2BQ5rCUXzE3MjU2MDgxODEuODczNjU2Nw%3D%3D%27/0.png', '["https://aigc-image.bj.bcebos.com/miaobi/5mao/b%2755yL5aSp5rGg6ZyA6KaB6L%2BQ5rCUXzE3MjU2MDgxODEuODczNjU2Nw%3D%3D%27/0.png"]', '安图县', 125.00, 4.9),
('防川风景区', '一眼望三国的边境景区，可同时看到中国、朝鲜、俄罗斯三国风光。', '防川位于吉林省珲春市，是中国、朝鲜、俄罗斯三国交界处。站在防川的龙虎阁上，可以清晰地看到三国的国土和图们江入海口的壮丽景色。', 'https://img2.baidu.com/it/u=4008082014,2831544927&fm=253&app=138&f=JPEG?w=500&h=501', '["https://img2.baidu.com/it/u=4008082014,2831544927&fm=253&app=138&f=JPEG?w=500&h=501"]', '珲春市', 60.00, 4.7);

-- 美食数据（部分）
INSERT INTO food (name, description, detail_content, cover_image, category, price_range, rating, popularity, is_featured) VALUES
('朝鲜族冷面', '延边最具代表性的特色美食，酸甜爽口，清凉解暑。', '朝鲜族冷面是延边地区的传统美食，以荞麦面为主料，配以牛肉汤、梨片、黄瓜丝、鸡蛋等。汤汁酸甜适中，面条筋道爽滑，是夏季消暑的最佳选择。', 'https://img1.baidu.com/it/u=4102894611,1348413967&fm=253&app=138&f=JPEG?w=500&h=652', '主食', '15-30元', 4.8, 9500, 1),
('朝鲜族打糕', '用糯米制成的传统糕点，口感软糯香甜。', '打糕是朝鲜族的传统节日食品，制作时将蒸熟的糯米放在木槽中用木槌反复捶打而成。可蘸黄豆粉、芝麻粉等食用，香甜可口。', 'https://pic.rmb.bdstatic.com/bjh/other/cc794a8790ee64302a25dad39a29c8b0.png?for=bg', '小吃', '10-20元', 4.7, 8200, 1);

-- 站长信息
INSERT INTO webmaster (name, avatar, signature, introduction, email, wechat) VALUES
('延边通', 'https://i.pravatar.cc/300?img=1', '用心介绍家乡，传播延边文化', '大家好，我是延边通，土生土长的延边人。创建这个网站是为了让更多人了解延边的美丽风光和独特文化。延边是我的家乡，这里有壮美的长白山、清澈的图们江、还有热情好客的朝鲜族人民。欢迎大家来延边做客！', 'yanbian@example.com', 'yanbian2024');

-- 宣传视频
INSERT INTO video (title, video_url, cover_image, duration, category, sort_order) VALUES
('延边朝鲜族自治州宣传片', 'https://my-bucket-i0z2qmk-1259428496.cos.ap-beijing.myqcloud.com/%E9%AD%85%E5%8A%9B%E5%BB%B6%E8%BE%B9%EF%BC%88%E5%BB%B6%E8%BE%B9%E5%B7%9E%E5%BD%A2%E8%B1%A1%E5%AE%A3%E4%BC%A0%E7%89%87%EF%BC%89%E8%8B%8F%E5%AE%87%E9%85%8D%E9%9F%B3%20%E6%A2%B5%E6%9B%B2%E9%85%8D%E9%9F%B3_mp4%20-%20%E5%88%9B%E6%84%8F%E7%89%87%E5%BA%93%20-%20%E7%88%B1%E7%BB%99%E7%BD%91.mp4 ', 'https://img1.baidu.com/it/u=1254094040,179712417&fm=253&fmt=auto&app=120&f=JPEG?w=1187&h=800', 180, '宣传', 1);
