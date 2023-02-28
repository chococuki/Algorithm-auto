import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int L, C;
	static List<Character> za = new ArrayList<>();	//자음 리스트
	static List<Character> mo = new ArrayList<>();	//모음 리스트
	static char[] password;
	static List<String> passwords = new ArrayList<>();
	
	//비밀번호 생성 함수
	public static void make(int L) {
		//모음 갯수
		for(int i=1; i<=Math.min(mo.size(), L-2); i++) {
			pickmo(i, 0, 0);
		}
	}
	
	//모음 선택 함수
	private static void pickmo(int mocnt, int start, int cnt) {
		if(cnt==mocnt) {
			pickza(mocnt, 0, 0);
		}
		
		for(int i=start; i<mo.size(); i++) {
				password[cnt] = mo.get(i);
				pickmo(mocnt, i+1, cnt+1);
		}
	}
	
	//자음 선택 함수
	public static void pickza(int mocnt, int start, int cnt) {
		//알파벳순 정렬후 저장
		if(mocnt+cnt==L) {
			char[] tmp = new char[password.length];
			for(int i=0; i<password.length; i++) {
				tmp[i] = password[i];
			}
			Arrays.sort(tmp);
			String str = "";
			for(char c:tmp) {
				str += c;
			}
			passwords.add(str);
			return;
		}
		
		for(int i=start; i<za.size(); i++) {
			password[mocnt+cnt] = za.get(i);
			pickza(mocnt, i+1, cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		password = new char[L];
		
		st = new StringTokenizer(br.readLine());
		char tmp;
		for(int i=0; i<C; i++) {
			tmp = st.nextToken().toCharArray()[0];
			if(tmp=='a' || tmp=='e' || tmp=='i' || tmp=='o' || tmp=='u') {
				mo.add(tmp);
			} else {
				za.add(tmp);
			}
		}
		
		make(L);
		
		//사전순으로 출력
		StringBuilder sb = new StringBuilder();
		Collections.sort(passwords);
		for(String s:passwords) {
			sb.append(s+"\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
