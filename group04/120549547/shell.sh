#!/bin/bash

git remote -v
read anykey
echo "抓取上游代码"
git fetch upstream  
echo "代码合并"
git merge upstream/master 
read anykey
echo "添加代码"
git add -A .		
read anykey
git commit -m "bobi"

echo "推送到githup"
read anykey
git push origin master 
read anykey
