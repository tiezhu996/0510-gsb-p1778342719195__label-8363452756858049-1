## 📖 项目简介

这是一个基于 **Spring Boot 3** 和 **Vue 3** 的全栈 Web 应用，旨在全方位展示延边朝鲜族自治州的独特魅力。项目集成了风景名胜展示、特色美食推荐、民俗文化介绍、旅游攻略分享以及实时在线互动功能。

## 🛠 技术栈

### 前端 (Frontend)
- **核心框架**: Vue 3.4
- **构建工具**: Vite 5.0
- **UI 样式**: WindiCSS (实用优先的 CSS 框架)
- **路由管理**: Vue Router 4
- **网络请求**: Axios 1.6
- **交互组件**: Swiper 11 (轮播图)

### 后端 (Backend)
- **核心框架**: Spring Boot 3.1.7
- **编程语言**: Java 17 (LTS)
- **持久层**: MyBatis Plus 3.5.5
- **数据库**: MySQL 8.0
- **工具库**: Hutool 5.8.25, Lombok
- **API 文档**: RESTful 标准

### 部署 (Deployment)
- **容器化**: Docker & Docker Compose
- **Web 服务器**: Nginx (Alpine)

## ✨ 功能特性

### 📱 沉浸式前端体验
- **视觉首页**: 动态轮播图展示延边四季风光，即时新闻动态滚动。
- **魅力视频**: 集成视频播放功能，直观感受延边人文气息。
- **全景介绍**: 结构化的家乡简介，支持多章节图文详情。
- **美食地图**: 汇集延边特色美食（冷面、石锅拌饭等），包含人气排行榜与详细介绍。
- **风景名胜**: 精选旅游景点推荐，支持分类浏览与搜索。
- **互动中心**: 站长个性化展示（实时时钟、联系方式）及在线问卷调查。

### 🔧 强大的后台服务
- **内容管理**: 轮播图、新闻、风景、美食、视频的 CRUD 全生命周期管理。
- **数据统计**: 浏览量统计、热门内容分析、问卷数据收集。
- **文件服务**: 本地文件存储与管理，支持多图片/视频上传。
- **扩展性**: 清晰的 Controller-Service-Mapper 分层架构，易于二次开发。

## 🚀 快速启动

### 前置要求
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (建议最新版)
- 端口占用检查: 确保 `3000` (Web), `8080` (API), `3306` (DB) 未被占用。

###  一键部署

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd yanbian-project
   ```

2. **启动服务**
   ```bash
   # 构建并启动所有服务（后台运行）
   docker compose up --build -d
   ```

3. **查看状态**
   ```bash
   docker compose ps
   ```
   *等待约 1-2 分钟，直到所有容器状态为 `Up (healthy)`*

### 🌐 访问应用

- **前端首页**: [http://localhost:3000](http://localhost:3000)
- **后端接口**: [http://localhost:8080/api/carousel/list](http://localhost:8080/api/carousel/list) (健康检查)
- **数据库**: `localhost:3306`
  - 用户: `yanbian`
  - 密码: `yanbian123`
  - 库名: `yanbian_db`

## 📦 项目结构

```
.
├── backend/                 # Spring Boot 后端工程
│   ├── src/main/java/com/yanbian/
│   │   ├── controller/      # API 控制器 (10个核心模块)
│   │   ├── service/         # 业务逻辑层
│   │   ├── mapper/          # 数据访问层 (MyBatis)
│   │   └── entity/          # 数据实体
│   ├── src/main/resources/
│   │   ├── db/              # SQL 初始化脚本
│   │   └── application.yml  # 全局配置
│   └── Dockerfile           # 后端镜像构建文件
├── frontend/                # Vue 3 前端工程
│   ├── src/
│   │   ├── api/             # 统一接口管理
│   │   ├── assets/          # 静态资源 (含 logo, styles)
│   │   ├── components/      # 通用组件
│   │   └── views/           # 页面试图 (Home, Food, Scenery...)
│   └── Dockerfile           # Nginx 前端镜像构建文件
└── docker-compose.yml       # 容器编排配置
```

## 🔍 数据表概览

项目启动后无需手动建表，Docker 会自动初始化以下 9 张表：

| 表名 (`yanbian_db`) | 描述 | 备注 |
| :--- | :--- | :--- |
| `carousel` | 轮播图管理 | 首页展示 |
| `news` | 新闻资讯 | 动态发布 |
| `introduction` | 家乡简介 | 城市概况 |
| `scenic_spot` | 风景名胜 | 旅游景点 |
| `food` | 特色美食 | 美食推荐 |
| `video` | 宣传视频 | 视频资源 |
| `webmaster` | 站长信息 | 关于我们 |
| `survey` | 问卷调查 | 用户反馈 |
| `upload_file` | 文件记录 | 附件管理 |

## 🛠 开发与调试

如果需要进行源码开发：

###由于前后端分离，您可以单独启动：

**前端 (Frontend)**
```bash
cd frontend
npm install
npm run dev
# 访问: http://localhost:5173
```

**后端 (Backend)**
```bash
cd backend
# 需确保本地 MySQL 已运行且配置正确
mvn spring-boot:run
# 访问: http://localhost:8080
```

## 📄 许可证
MIT License

---
<div align="center">
  Designed & Developed for <strong>Yanbian</strong> ❤️
</div>
