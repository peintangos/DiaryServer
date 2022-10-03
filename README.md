# DiaryServer
日記のサーバーです。

## MySQLServerの立て方
- docker-compose up -d
- docker-compose exec mysqldb /bin/bash
- mysql -u docker -pdocker

## git について
- git plog 
- git commit --fixup 該当のコミットに対して、fixupをつける。
- git rebase -i HEAD~n 直近n個のコミットをまとめる。
