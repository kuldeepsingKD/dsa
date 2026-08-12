class Solution {
    public int numFriendRequests(int[] ages) {
         int n = ages.length;
        Arrays.sort(ages);
        int count=0;
        for(int i=n-1;i>=0;i--)
            {
                for(int j=i-1;j>=0;j--)
                    {
                        if(ages[j]> (0.5*ages[i]+7))
                        {
                            count++;
                            if(ages[i]==ages[j])count++;
                        }
                        else break;
                    }
            }
        return count;
    }
}