
class Solution {

    private static final int LIMIT = 1_000_001;
    private int[] primes;

    public String smallestPalindrome(String s, int k) {
        buildPrimes(5000);

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int[] half = new int[26];
        char mid = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1) mid = (char) ('a' + i);
        }

        if (countWays(half) < k) return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                int ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if (mid != 0) ans.append(mid);
        ans.append(left.reverse());

        return ans.toString();
    }

    private int countWays(int[] cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long res = 1;

        for (int p : primes) {
            if (p > total) break;

            int e = factExp(total, p);
            for (int x : cnt) {
                if (x > 1) e -= factExp(x, p);
            }

            while (e-- > 0) {
                if (res > LIMIT / p) return LIMIT;
                res *= p;
            }
        }

        return (int) Math.min(res, LIMIT);
    }

    private int factExp(int n, int p) {
        int e = 0;
        while (n > 0) {
            n /= p;
            e += n;
        }
        return e;
    }

    private void buildPrimes(int n) {
        boolean[] comp = new boolean[n + 1];
        int cnt = 0;

        for (int i = 2; i <= n; i++) {
            if (!comp[i]) {
                cnt++;
                if ((long) i * i <= n) {
                    for (int j = i * i; j <= n; j += i) {
                        comp[j] = true;
                    }
                }
            }
        }

        primes = new int[cnt];
        int idx = 0;
        for (int i = 2; i <= n; i++) {
            if (!comp[i]) primes[idx++] = i;
        }
    }
}