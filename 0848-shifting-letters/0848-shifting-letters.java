class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int x = 0;
        StringBuilder ans = new StringBuilder();

        for(int shift : shifts) {
            x = (x + shift) % 26;
        }

        for(int i=0; i < s.length(); i++) {
            int id = s.charAt(i) - 'a';
            ans.append((char) ((id + x) % 26 + 'a'));


            x = ((x - shifts[i]) % 26 + 26) % 26;
        }

        return ans.toString();
    }
}