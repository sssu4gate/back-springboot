#베이스 이미지 지정
FROM openjdk:8-jdk-alpine
#빌드시에 필요한 인자 JAR_FILE을 build/libs애 있는 jar파일을 지정한다.
ARG JAR_FILE=build/libs/gate-0.0.1-SNAPSHOT.jar
#인자 JAR_FILE을 application.jar이라는 이름으로 복사한다.
COPY ${JAR_FILE} application.jar
#컨테이너 실행시에 해당 jar파일을 실행하라고 명령한다.
ENTRYPOINT ["java","-jar","/application.jar"]
#8080포트에 할당
EXPOSE 8080
