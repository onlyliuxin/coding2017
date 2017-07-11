@echo off
setlocal ENABLEDELAYEDEXPANSION

:main
	call :ini
	cd /d E:\12.repolist\41689722.eulerlcs\2.code
	call :del_resource
	call :end
	@echo on
	
goto :eof
:del_resource
	for /f "usebackq delims==" %%a in (`dir /b/s/ad-h ".settings"`) do rmdir /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/ad-h ".metadata"`) do rmdir /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/ad-h "target"`) do rmdir /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/ad-h "RemoteSystemsTempFiles"`) do rmdir /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/ad-h ".recommenders"`) do rmdir /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/ad-h ".apt_generated"`) do rmdir /s/q %%a

	for /f "usebackq delims==" %%a in (`dir /b/s/a-d-h ".classpath"`) do del /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/a-d-h ".project"`) do del /s/q %%a
	for /f "usebackq delims==" %%a in (`dir /b/s/a-d-h ".factorypath"`) do del /s/q %%a

goto :eof
:end
	@echo.
	@echo.
	pause

goto :eof
:ini
	set self_path="%cd%"
	set yyyymmdd=%date:~0,4%%date:~5,2%%date:~8,2%
	set yymmdd=!yyyymmdd:~2,6!
	set hhmmss=%time:~0,2%%time:~3,2%%time:~6,2%
	if "!hhmmss:~0,1!" == " " set hhmmss=0!hhmmss:~1,7!
goto :eof
