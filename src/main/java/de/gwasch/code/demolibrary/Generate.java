package de.gwasch.code.demolibrary;

import de.gwasch.code.escframework.components.utils.CodeGenerator;

public class Generate {
	public static void main(String[] args) throws Exception {		
		
		CodeGenerator generator = new CodeGenerator("src/main/java", Generate.class.getPackageName(), "src/main/gen");
		generator.generateInterfaces();
	}
}