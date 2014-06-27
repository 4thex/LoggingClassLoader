To run this application with the LoggingClassLoader and the FileClassLoadingListener, make the following modifications to the %CATALINA_BASE%/cataline.properties file:
Add the following path entry to the end of your common.loader property:
	C:/Users/4thex/ExecutionRecorder/Distribution/*.jar
Please note that the actual path to the Distribution folder will be different for you.

The 'WEB-INF/context.xml' file must contain the following Loader element inside the Context element:
	<?xml version="1.0" encoding="UTF-8"?>
	<Context>
	    <Loader loaderClass="com._4thex.classloader.LoggingTomcatClassLoader" />
	</Context>

After running the application, a file name 'ClassLoader.properties' will be generated in the current folder.
This file contains the loaded classes, their location, and comments about what triggered the loading.

Example:
...
# Initially requested from org.apache.catalina.startup.WebAnnotationSet#loadApplicationServletAnnotations
com._4thex.web.ExampleServlet=C:\Users\4thex\ExecutionRecorder\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\ExampleApplication\WEB-INF\classes
...
