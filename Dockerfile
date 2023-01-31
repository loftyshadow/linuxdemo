# 设置本镜像需要使用的基础镜像
FROM  openjdk:17.0-oracle
# 把jar包添加到镜像中
WORKDIR app
ADD linuxdemo.jar /app/app.jar
# 镜像暴露的端口
EXPOSE 8085
CMD java -jar /app/app.jar



#2、制作镜像
 #docker build -t test .
 #
 ##将app.jar映射到/root/ancen/sp/huoma.jar文件，将日志文件/logger/映射到/root/ancen/sp/logger下
 #docker run --name test -d -p 8081:8081 -v /root/ancen/sp/huoma.jar:/app.jar/ -v /root/ancen/sp/logger:/logger/ test
 #
 #cat 查看文件记事本
 #
 ##修改开启自启动
 #docker update --restart=always xxx
 #
 #docker run -dit --name Myrabbitmq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 -p 15672:15672 -p 5672:5672 rabbitmq:managemen
 #
 #docker run --name netty-a -d -p 8081:8081 -p 8090:8090 -v /data/netty/netty-server.jar:/app.jar/ -v /data/netty/logger:/logger/ netty-a