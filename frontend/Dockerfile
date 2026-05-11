# 构建阶段
ARG APP_VERSION
FROM node:20-alpine AS builder
WORKDIR /app
ARG APP_VERSION

# 复制 package 文件
COPY package*.json ./

# 配置 npm 镜像源并安装依赖
RUN npm config set registry https://registry.npmmirror.com && \
    npm install

# 更新版本号
RUN npm version ${APP_VERSION} --no-git-tag-version

# 复制源代码并构建
COPY . .
RUN npm run build

# 生产阶段
FROM nginx:alpine
WORKDIR /usr/share/nginx/html

# 从构建阶段复制构建产物
COPY --from=builder /app/dist .

# 复制 nginx 配置
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
