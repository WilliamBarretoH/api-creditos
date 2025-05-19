# Stage 1: build Angular
FROM node:18-alpine AS build
WORKDIR /app

# Habilita o legacy provider para que o Webpack funcione no Node 18+
ENV NODE_OPTIONS=--openssl-legacy-provider

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Stage 2: serve static via Nginx
FROM nginx:alpine
COPY --from=build /app/dist/creditos-frontend /usr/share/nginx/html
EXPOSE 80
