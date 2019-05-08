public class Teste{
	public static void main(String[] args) {
		String pattern = "\\d+(\\.)?\\d+(\\.)?(-)?";
		if("222222222o22".matches(pattern)){
			System.out.println("True");
		}
	}
}