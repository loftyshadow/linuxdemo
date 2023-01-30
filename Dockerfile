# 设置本镜像需要使用的基础镜像
FROM  openjdk:17.0-oracle
# 把jar包添加到镜像中
WORKDIR app
ADD linuxdemo.jar /app/app.jar
# 镜像暴露的端口
EXPOSE 8085
CMD java -jar /app/app.jar
