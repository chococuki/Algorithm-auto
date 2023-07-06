import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int K;
    static int result = -1;
    static HashSet<String> hashSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] numbers = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        changeNumber(numbers, 0);

        System.out.println(result);
    }

    public static char[] copyCharArray(char[] numbers) {
        char[] array = new char[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            array[i] = numbers[i];
        }

        return array;
    }

    public static void changeNumber(char[] numbers, int dept) {
        if(dept == K) {
            result = Math.max(result, toNumber(numbers));
            hashSet.add(dept + Arrays.toString(numbers));
            return;
        } else if(K%2 == dept%2) {
            if(hashSet.contains(dept + Arrays.toString(numbers))) {
                return;
            } else {
                hashSet.add(dept+ Arrays.toString(numbers));
            }
        }

        for(int f = 0; f < numbers.length; f++) {
            for(int t = f + 1; t < numbers.length; t++) {
                char tmp = numbers[f];
                numbers[f] = numbers[t];
                numbers[t] = tmp;

                if(numbers[0] != '0') {
                    changeNumber(numbers, dept+1);
                }

                tmp = numbers[f];
                numbers[f] = numbers[t];
                numbers[t] = tmp;
            }
        }
    }

    public static int toNumber(char[] numbers) {
        StringBuilder sb = new StringBuilder();

        for(char c : numbers) {
            sb.append(c);
        }

        return Integer.parseInt(sb.toString());
    }
}