package utilities;

public class SplitString {

	public static void main(String[] args) {
		String Main = "\r\n" + 
				"                                        Amir, Mr Mohammad | Manmeet Singh | November 14th, 2017, 13:41 | Pre Release | SFA2212178 | Due on November 14th, 2017, 15:18                                    ";
		String[] arrSplit = Main.split("SFA2212178");
		String gg1 = arrSplit[0];
		System.out.println(gg1);
		
		String gg2 = gg1.substring(gg1.length()-14,gg1.length());
		System.out.println(gg2);
		
		String actual=gg2.substring(0, 11);
		System.out.println(actual);
		
//		String[] gg3 = gg2.split(" |");
//		String gg4 = gg3[0];
//		System.out.println(gg4);
	}

}
