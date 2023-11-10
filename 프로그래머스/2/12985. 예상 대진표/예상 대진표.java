class Solution
{
    public int solution(int n, int a, int b)
    {
        int round = 0;
        while(a != b) {
            a = ((a-1)/2)+1;
            b = ((b-1)/2)+1;
            System.out.println(a+" "+b);
            round++;
        }
        return round;
    }
}