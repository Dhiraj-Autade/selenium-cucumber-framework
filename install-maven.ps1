# Maven Installation Script for Windows
# Run as Administrator

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   Maven Installation for Windows" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Check if running as Administrator
$isAdmin = ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
if (-not $isAdmin) {
    Write-Host "ERROR: Please run this script as Administrator!" -ForegroundColor Red
    Write-Host "Right-click PowerShell and select 'Run as Administrator'" -ForegroundColor Yellow
    exit 1
}

# Step 1: Create Maven directory
Write-Host "Step 1: Creating Maven directory..." -ForegroundColor Green
$mavenDir = "C:\tools\maven"
if (-not (Test-Path $mavenDir)) {
    New-Item -ItemType Directory -Path $mavenDir -Force | Out-Null
    Write-Host "Created: $mavenDir" -ForegroundColor Green
} else {
    Write-Host "Directory already exists: $mavenDir" -ForegroundColor Green
}

# Step 2: Download Maven
Write-Host ""
Write-Host "Step 2: Downloading Maven 3.9.6..." -ForegroundColor Green
$mavenUrl = "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
$downloadPath = "$mavenDir\apache-maven-3.9.6-bin.zip"

try {
    Write-Host "Downloading from: $mavenUrl" -ForegroundColor Yellow
    [Net.ServicePointManager]::SecurityProtocol = [Net.ServicePointManager]::SecurityProtocol -bor [Net.SecurityProtocolType]::Tls12
    Invoke-WebRequest -Uri $mavenUrl -OutFile $downloadPath -ErrorAction Stop
    Write-Host "Downloaded: $downloadPath" -ForegroundColor Green
}
catch {
    Write-Host "ERROR: Failed to download Maven" -ForegroundColor Red
    Write-Host "Please download manually from: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    exit 1
}

# Step 3: Extract Maven
Write-Host ""
Write-Host "Step 3: Extracting Maven..." -ForegroundColor Green
try {
    Expand-Archive -Path $downloadPath -DestinationPath $mavenDir -Force -ErrorAction Stop
    Write-Host "Extracted successfully" -ForegroundColor Green
    Remove-Item $downloadPath -Force
    Write-Host "Cleaned up downloaded zip file" -ForegroundColor Green
}
catch {
    Write-Host "ERROR: Failed to extract Maven" -ForegroundColor Red
    exit 1
}

# Step 4: Set Environment Variables
Write-Host ""
Write-Host "Step 4: Setting Environment Variables..." -ForegroundColor Green

$mavenHome = "C:\tools\maven\apache-maven-3.9.6"

# Set MAVEN_HOME
[Environment]::SetEnvironmentVariable("MAVEN_HOME", $mavenHome, "Machine")
Write-Host "Set MAVEN_HOME=$mavenHome" -ForegroundColor Green

# Add to PATH
$currentPath = [Environment]::GetEnvironmentVariable("PATH", "Machine")
$mvnBinPath = "$mavenHome\bin"

if ($currentPath -notlike "*$mvnBinPath*") {
    $newPath = "$currentPath;$mvnBinPath"
    [Environment]::SetEnvironmentVariable("PATH", $newPath, "Machine")
    Write-Host "Added Maven bin to PATH" -ForegroundColor Green
} else {
    Write-Host "Maven bin already in PATH" -ForegroundColor Green
}

# Update current session PATH
$env:MAVEN_HOME = $mavenHome
$env:Path += ";$mvnBinPath"

# Step 5: Verify Installation
Write-Host ""
Write-Host "Step 5: Verifying Installation..." -ForegroundColor Green
Write-Host ""

try {
    $mvnVersion = & mvn --version 2>&1
    Write-Host $mvnVersion -ForegroundColor Green
    Write-Host ""
    Write-Host "Maven installed successfully!" -ForegroundColor Green
}
catch {
    Write-Host "WARNING: Could not verify Maven in current session" -ForegroundColor Yellow
    Write-Host "Please close and reopen PowerShell for changes to take effect" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   Installation Complete!" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "NEXT STEPS:" -ForegroundColor Cyan
Write-Host "1. Close this PowerShell window completely" -ForegroundColor Yellow
Write-Host "2. Open a NEW PowerShell window" -ForegroundColor Yellow
Write-Host "3. Navigate to your project:" -ForegroundColor Yellow
Write-Host "   cd C:\Users\91766\Downloads\Selenium\selenium-cucumber-automation-framework" -ForegroundColor Cyan
Write-Host "4. Run: mvn clean install" -ForegroundColor Cyan
Write-Host ""
