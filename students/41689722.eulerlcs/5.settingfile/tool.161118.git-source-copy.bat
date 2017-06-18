@echo off
setlocal ENABLEDELAYEDEXPANSION

rem .* 
:main
	call :ini
	
	set path_root=d:\abc
	set path_desc=pg.%yymmdd%.%hhmmss%
	
	call :copy_source ""
	call :copy_source ""

	call :end
	@echo on
	
goto :eof
:copy_source
	if %1=="" goto :eof
	set file_name=%~1
	set file_name=!file_name:/=\!
	@echo f | xcopy /r/y/s %path_root%\!file_name!		%path_desc%\!file_name!

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
