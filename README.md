# Sergey-Prokopenko
Epam course

1. Check      
    $java -version      
    $export JAVA_HOME = ...    
    $mvn -version    
2. Build    
    $mvn clean install    
3. Preparing repotrs  
    $mvn site  
    $mvn site:stage  
    check: ``<project>/target/stage/index.html``

mvn cobertura:cobertura -- % coverage of tests
checkstyle:check
