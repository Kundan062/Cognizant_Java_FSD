$projects = @("account", "loan", "eureka-discovery-server", "api-gateway")

foreach ($p in $projects) {
    Write-Host "Generating $($p)..."
    $url = "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.4.1&baseDir=$($p)&groupId=com.cognizant&artifactId=$($p)&name=$($p)&packaging=jar&javaVersion=17"
    Invoke-WebRequest -Uri $url -OutFile "$($p).zip"
    Expand-Archive -Path "$($p).zip" -DestinationPath "." -Force
    Remove-Item "$($p).zip"
}
Write-Host "Done."
