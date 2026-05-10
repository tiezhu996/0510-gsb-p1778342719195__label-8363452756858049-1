FROM node:20-alpine AS builder
WORKDIR /app

ARG APP_VERSION=1.0.0

COPY package*.json ./

RUN npm config set registry https://registry.npmmirror.com && \
    npm install

COPY . .

ENV VUE_APP_VERSION=${APP_VERSION}
RUN npm run build

FROM nginx:alpine
WORKDIR /usr/share/nginx/html

ARG APP_VERSION=1.0.0

COPY --from=builder /app/dist .

COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 3000

ENV APP_VERSION=${APP_VERSION}

CMD ["nginx", "-g", "daemon off;"]
