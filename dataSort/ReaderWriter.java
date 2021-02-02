package dataSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class ReaderWriter {

	public static void readwrite() throws IOException, SQLException {
		// setting up needed variables, creating files for writing and opening a reader
		// for the csv data
		String line = null;
		int countReceived = 0;
		int countFailed = 0;
		int countSuccess = 0;
		FileWriter fileWriter = null;
		FileWriter fileWriter2 = null;
		BufferedReader br = new BufferedReader(new FileReader("ms3Interview.csv"));
		fileWriter = new FileWriter("ms3Interview-bad.csv");
		fileWriter2 = new FileWriter("ms3Interview.log");
		line = br.readLine();
		// reads through csv file and writes data to its respective location
		while ((line = br.readLine()) != null && line.length() != 0) {
			// accounting for double comma(,,) being null
			if (!line.matches(".*?,,+.*")) {
				DataBase.dbAddBatch(line);
				countSuccess++;

			} else {
				fileWriter.append(line);
				fileWriter.append(System.lineSeparator());
				countFailed++;
			}
			countReceived++;
		}
		// finishes reports
		fileWriter2.append("Amount Received: " + String.valueOf(countReceived) + System.lineSeparator());
		fileWriter2.append("Amount Successful: " + String.valueOf(countSuccess) + System.lineSeparator());
		fileWriter2.append("Amount Failed: " + String.valueOf(countFailed) + System.lineSeparator());
		fileWriter2.close();
		fileWriter.close();
		br.close();
	}
}