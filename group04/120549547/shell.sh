#!/bin/bash

git remote -v
read anykey
git fetch upstream  #抓取上游代码
git merge upstream/master #代码合并
read anykey

git add -A .		#添加代码
read anykey
git commit -m "bobi"
read anykey
git push origin master  #推送到githup
read anykey
