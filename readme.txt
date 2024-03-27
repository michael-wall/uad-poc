
POC to convert the XML based output from the Liferay DXP UAD tool to CSV. A single CSV file will be generated per Model or per Liferay Object definition based on the source files.

POC was built and tested with JDK 1.8 on Windows 10. It uses a Gradle based Liferay Workspace but has no dependencies on the Liferay DXP product, so the Liferay DXP version specified in gradle.properties > liferay.workspace.product is not important.

Prerequisites:

Java 1.8 or later.

Steps to run (with a compiled XML-to-CSV-converter-1.0.0.jar): 

1. Generate and download the UAD zip file(s) from the Liferay DXP environment.
2. Extract the zip file(s).
3. Run the code with the following command (with appropriate arguments) from the folder containing the XML-to-CSV-converter-1.0.0.jar JAR file:

java -cp XML-to-CSV-converter-1.0.0.jar com.mw.uad.XMLToCSV [arg_source_folder] [arg_output_folder] [arg_debug_logging]

For example:

java -cp XML-to-CSV-converter-1.0.0.jar com.mw.uad.XMLToCSV C:\mw_temp\source\ C:\mw_temp\output\ true

java -cp XML-to-CSV-converter-1.0.0.jar com.mw.uad.XMLToCSV C:\mw_temp\source\ C:\mw_temp\output\ false

where:

[arg_source_folder] is the folder containing the extracted zip file(s).
[arg_output_folder] is the folder that the generated CSV file(s) are written to.
[arg_debug_logging] is a boolean that determines if the debug logging is generated.

Note that both folders must already exist, be accessible to the java process, must not contain spaces and the arguments must end in the \ character.

4. The CSV file(s) will be created in the [output_folder] and a summary will be displayed e.g.

Done... 1553 XML files merged into 31 CSV files.