
POC to convert the XML based output from the Liferay DXP UAD tool to CSV. A single CSV file will be generated per Model or per Liferay Object definition etc.

This was built and tested with JDK 1.8 on Windows 10.

Steps to run: 

1. Generate and download the UAD zip file from Liferay DXP.
2. Extract the zip file.
3. Run the code with the following command:

java -cp XML-to-CSV-converter-1.0.0.jar com.mw.uad.XMLToCSV [source_folder] [output_folder]

For example:

java -cp XML-to-CSV-converter-1.0.0.jar com.mw.uad.XMLToCSV C:\mw_temp\source\ C:\mw_temp\output\

where [source_folder] is the folder containing the extracted zip file and [output_folder] is the folder that the generated CSV file(s) are written to.

Note that both folders must exist, must not contain spaces and the arguments must end in \

4. The XML files will be created in the [output_folder].