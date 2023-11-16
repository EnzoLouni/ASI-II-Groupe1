FROM node:latest

WORKDIR /app

EXPOSE 3000

ENV REACT_APP_RPROXY=http://localhost:80/ \
    REACT_APP_IS_MOCK=false

COPY ["package.json", "package-lock.json*", "./"]

RUN npm install 

COPY . .

CMD ["npm", "start"]