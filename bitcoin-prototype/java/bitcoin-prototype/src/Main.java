import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        List<String> transactions = new ArrayList<>();
        transactions.add("Alice -> Bob : 10 BTC");
        transactions.add("Bob -> Charlie : 5 BTC");


        Block block = new Block(
                0,
                0,
                "",
                "merkle-root-fake",
                3,
                "0000000000000",
                transactions
        );


        block.addTrasaction("Charlie -> Dave : 2 BTC");


        System.out.println("Minerando bloco...");
        block.mineBlock(3);


        System.out.println("Hash calculado: " + block.calculatehash());


        Map<String, Object> blockMap = block.toMap();

        System.out.println("\n=== Block como Map ===");
        for (Map.Entry<String, Object> entry : blockMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
