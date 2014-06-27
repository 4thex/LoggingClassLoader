To run this application with the LoggingClassLoader and the FileClassLoadingListener, use the following commands:
set CLASSPATH=bin;C:\Users\4thex\Downloads\eclipse-standard-kepler-SR2-win32-x86_64\eclipse\plugins\org.junit_4.11.0.v201303080030\junit.jar
java -Djava.system.class.loader=com._4thex.classloader.LoggingClassLoader -cp %CLASSPATH%;../Distribution/LoggingClassLoader.jar;../Distribution/ClassLoadingListener.jar;../Distribution/ClassLoadingListenerImpl.jar com._4thex.Main

Please note that your actual path to junit.jar will be different for you.
The command must be run from the project root; the location of this file.

After running the application, a file name 'ClassLoader.properties' will be generated in the current folder.
This file contains the loaded classes, their location, and comments about what triggered the loading.

Example:
...
# Initially requested from com._4thex.Main#main
junit.framework.TestCase=C:\Users\4thex\Downloads\eclipse-standard-kepler-SR2-win32-x86_64\eclipse\plugins\org.junit_4.11.0.v201303080030\junit.jar
...
