public class Demo04 {
    /*
    Prove that if some operation takes O(N) time for the first N iterations and O(N²) time for the N+1 operation,
    the amortized runtime is O(N). Remember that you can think of amortized runtime as the average runtime.
    对于N+1次操作的TotalCost = N*O(N) + O(N²) = O(N²) + O(N²) = O(N²), 则amortized cost = O(N²)/(N+1) = O(N)
     */

    /*
    Is it possible to have an Ω bound and O bound that are different for the best case?
    先考虑下面的linearSearch，best case是在arr[0]就命中target，这个时候上界O(1)，下界Ω(1)
    再考虑下面的findMin，不论什么输入都要遍历整个数组，best case的上界O(N)，下界Ω(N)，best case的上下界一定一致吗？？
    其实可以不一致，因为O和Ω没有要求tightest，例如在linearSearch中，说上界为O(N)也可以，所以it is possible
     */
    public int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    public int findMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }

    /*
    下面就是一个插入排序，前i项有序，理论最高复杂度N²
     */
    public void genomes(String[] args) {
        int N = Integer.parseInt(args[0]);
        String[] genomes = new String[N];
        for (int i = 0; i < N; i++) {
            In gfile = new In("genomeFile" + i + ".txt");
            genomes[i] = gfile.readString();
        }

        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (genomes[j-1].length() > genomes[j].length()) {
                    String tmp = genomes[j - 1];
                    genomes[j - 1] = genomes[j];
                    genomes[j] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    /*
    求tightest possible bounds？
    如果要结束的话，必须命中case2，分母为0的时候自然就结束了，问题变成bet执行多少次能到0？
    先看best case，就是先命中case2，bet变成2，然后命中case1，bet变成0，再命中case2，就出来了，3次执行，Ω(1)
    再看worst case，最差情况就是一直命中bet变成2的情况，永远不会结束，那么是infinite bound？
     */
    public static void caGreeno(int bet, int N) {
        double somenum = Math.random(); //returns a double from 0 to 1
        double newnum;
        if (somenum < .5){
            newnum = Math.random();
            if(newnum < .5){
                caGreeno(bet/2, N);
                caGreeno(bet/2, N);
            }
            else{
                caGreeno(bet * 2/bet , N);
            }
        }
        else{
            newnum = Math.random();
            if(newnum < .5){
                caGreeno(bet - 50 , N);
                caGreeno(bet - 100, N);
            }
            else{
                caGreeno(bet - 1, N);
            }
        }
    }

    /*
    这个会栈溢出，N只要大于1，若干次开根号后永远是1
     */
    public void tree(int N){
        for(int i = 0; i < N; i++){
            System.out.println("Oi I'm a tree and I can't get up");
        }
        tree((int) Math.sqrt(N));
    }

    public static void main(String[] args) {
        String sql = "SELECT DISTINCT ac.chain_action_id, ac.start_person_type, ac.start_person_id, "
                + "ac.start_person_uuap, ac.start_person_name, ac.approval_type, "
                + "ac.custom_approval_person, ac.custom_approval_person_name, ac.approval_chain_id, "
                + "ac.approval_chain_policy, ac.approval_chain_type, ac.start_person_level, "
                + "ac.end_person_level, ac.start_position_level, ac.end_position_level, ac.approval_level, ac" +
                ".is_use_job_money_filter, "
                + "ac.is_reference_authorization, ac.is_covery_money, ac.is_covery_money_index, "
                + "head.amount_filter_head_id, head.amount_filter_head_name, ab.*, "
                + "rv.variable_name as save_variable " +
                " FROM action_base ab LEFT JOIN action_chain ac " +
                " ON ac.is_enable = '1' " +
                " AND ac.business_id =  " + 88 +
                " AND ( ab.return_type = '" + 5 + "'" +
                "  OR ab.return_type = '" + 6 + "'" +
                " ) AND ab.return_type_id = ac.chain_action_id  " +
                " LEFT JOIN amount_action_relation relation " +
                " ON relation.is_enable = '1' " +
                " AND relation.business_id = " + 88 +
                " AND relation.chain_action_id = ac.chain_action_id " +
                " LEFT JOIN amount_filter_head head " +
                " ON head.is_enable = '1' " +
                " AND head.business_id = " + 88 +
                " AND head.amount_filter_head_id = relation.amount_filter_head_id " +
                " LEFT JOIN rule_variable rv " +
                " ON rv.is_enable = '1' " +
                " AND rv.variable_id = ab.save_variable_id " +
                " WHERE ab.is_enable = '1' " +
                " AND ab.business_id = " + 88 +
                " AND ab.base_action_id = " + 88;
        System.out.println(sql);
    }
}
