# 도커/쿠버네티스 1주차
## 도커 설치
- uname -r : 3버전 이상이여야 함
- sudo curl -fsSL [https://download.docker.com/linux/ubuntu/gpg](#)(https://download.docker.com/linux/ubuntu/gpg) | sudo apt-key add -
- sudo add-apt-repository "deb [arch=amd64](#) [https://download.docker.com/linux/ubuntu](#)(https://download.docker.com/linux/ubuntu) $(lsb_release -cs) stable"
- sudo apt-get update
- sudo apt-get install docker-ce
- docker -v : 설치확인

## sudo 없이 docker 명령어 쓰기
- sudo usermod -aG docker \<현재 유저이름\>
	- 현재 유저이름 확인 : whoami했는데 `docker: Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Post "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/containers/create": dial unix /var/run/docker.sock: connect: permission denied` 에러뜨면 **sudo chmod 666 /var/run/docker.sock** 로 해당 파일 권한을 변경시켜주면 된다.

## 도커 컨테이너 기본 명령어
- 기본적으로 run 사용하지만, create도 가능하다.
	- run : 실행된 컨테이너 내부로 들어감
![](Screen%20Shot%202022-06-16%20at%202.22.41%20PM.png)
- exit : 컨테이너 정지시키고 쉘에서 빠져나옴
- control + P,Q : 컨테이너 유지시키고 빠져나옴
- docker rm -f $(docker ps -a -q) : 실행중인 컨테이너 모두 바로 삭제
## 컨테이너 어플리케이션 구축
- mysql 설치 :
`docker run -d --name wordpressdb -e MYSQL_ROOT_PASSWORD=hcshcs -e MYSQL_DATABASE=wordpress mysql:5.7`
- WordPress 설치 : 
`docker run -d -e WORDPRESSDBHOST=mysql -e WORDPRESSDBUSER=root -e WORDPRESSDBPASSWORD=hcshcs --name wordpress --link wordpressdb:mysql -p 80 wordpress`
- `-d` vs `-it` : -d는 백그라운드로 실행해야하는 컨테이너들 mysql,springboot 등등,-it는 쉘에 접속해 명령어를 입력하고 상호 입출력이 필요한 컨테이너들에 옵션 추가
- 그러면 -d 옵션으로 실행된 백그라운드 컨테이너들의 쉘에는 어떻게 접근할까?`docker exec -it wordpressdb /bin/bash`사용해서 접근 후 빠져나오면 된다.
