@Echo off

set REMOTE_PROJECT=git@github.com:reflexdemon/demo.git
Echo "Pulling from %REMOTE_PROJECT%"
git pull %REMOTE_PROJECT%

git status

Echo "Adding newely added resources in notes, src and webapps"
git add notes\*
git add src\*
git add webapps\*

git status


Echo "committing the changes with Version from GIT HUB %DATE% %TIME%"
git commit -am "Version from GIT HUB %DATE% %TIME%"


Echo "We are all set to push the changes to the production!"
git push
