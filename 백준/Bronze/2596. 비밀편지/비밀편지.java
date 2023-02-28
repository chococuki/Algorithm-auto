import java.util.*;

public class Main {
	public static String[] board = {"000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010"};
	
	public static String check(String num) {
		switch (num) {
		case "000000":	return "A";
		case "001111":	return "B";
		case "010011":	return "C";
		case "011100":	return "D";
		case "100110":	return "E";
		case "101001":	return "F";
		case "110101":	return "G";
		case "111010":	return "H";		
		default:		return "false";
		}
	}
	
	public static String check2(String num) {
		String[] numS = num.split("");
		for(int i=0; i<8; i++) {
			int cnt=0;
			String[] tmp = board[i].split("");
			for(int j=0; j<6; j++) {
				if(!numS[j].equals(tmp[j])) cnt++;
				if(cnt>=2) break;
			}
			if(cnt<2) return check(board[i]);
		}
		return "false";
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		String[] tmp = in.next().split("");
		
		String[] numbers = new String[n];
		for(int i=0; i<n; i++) {
			String t = "";
			for(int j=0; j<6; j++) {
				t += tmp[i*6+j];
			}
			numbers[i] = t;
		}
		
		String result = "";
		for(int i=0; i<n; i++) {
			if(check(numbers[i]).equals("false")) {
				String ck2 = check2(numbers[i]);
				if(ck2.equals("false")) {
					result = Integer.toString(i+1);
					break;
				} else {
					result += ck2;
				}
			} else {
				result += check(numbers[i]);
			}
		}
		
		System.out.println(result);
		
	}
}