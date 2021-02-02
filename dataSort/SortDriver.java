package dataSort;

//This program accepts data from a file named ms3Interview.csv and splits it 
//into a database for good data or a csv for bad(incomplete) data
//it also creates a record of the number of entries processed
public class SortDriver {

	public static void main(String[] args) throws Exception {
		DataBase.dbSetup();
		ReaderWriter.readwrite();
		DataBase.dbClean();
	}

}
