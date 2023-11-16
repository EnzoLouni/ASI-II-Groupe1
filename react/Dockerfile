FROM node:latest

WORKDIR /app

EXPOSE 3000

ENV REACT_APP_RPROXY=reverseproxy:80/

COPY ["package.json", "package-lock.json*", "./"]

RUN npm install 

COPY . .

CMD ["npm", "start"]