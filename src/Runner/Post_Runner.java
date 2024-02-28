package Runner;

import java.io.IOException;

import TestPackage.Test_Case_1;
import TestPackage.Test_Case_2;

public class Post_Runner {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Test_Case_1.executor();
		Test_Case_2.executor();
	}

}

