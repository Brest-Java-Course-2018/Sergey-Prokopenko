[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/Sergey-Prokopenko.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/Sergey-Prokopenko)

[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/Sergey-Prokopenko/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/Sergey-Prokopenko)

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

mvn cobertura:cobertura -- coverage of tests

mvn checkstyle:check

4. Travis CI integration https://travis-ci.org/Brest-Java-Course-2018/Sergey-Prokopenko/

