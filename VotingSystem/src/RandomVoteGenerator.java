import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by smallaro on 12/3/16.
 */
public class RandomVoteGenerator {
    public static void generateOneHundredThousandRandomVotes() {
        Database test = new Database();
        Random rand = new SecureRandom();
        ArrayList<Integer> idList = test.getCandidateIDList();
        int[] ids = new int[50000];
        for(int i=0; i< ids.length; i++) {
            ids[i] = (idList.get(rand.nextInt(idList.size())));
        }
        test.voteID(ids);
        test.closeConnection();
    }
}
