Write-Host "Stopping containers..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe stop"

$Folder = '.\data'
if (Test-Path -Path $Folder) {
    Write-Host "Removing data folder..."
    Remove-Item $Folder -Recurse
}


Write-Host "Removing container for  sensor-api service..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe rm --force process-api"

Write-Host "Build new image for process-api service..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe build --no-cache sensor-api"

Write-Host "Build new image for process-api service..." -ForegroundColor Green
Invoke-Expression -Command "docker-compose.exe up"
