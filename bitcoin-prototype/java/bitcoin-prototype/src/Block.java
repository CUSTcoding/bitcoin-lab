import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

public class Block {

    private int index;
    private long timeStamp;
    private LocalDate data;
    private long nonce;
    private String hash;
    private String markleRoot;
    private int dificulty;
    private String previusBlockHash;
    private List<String> transactions;

    public Block(int index, long nonce, String hash, String markleRoot, int dificulty, String previusBlockHash, List<String> transactions){
        this.index = index;
        this.timeStamp = Instant.now().getEpochSecond();
        this.data = LocalDate.now();
        this.nonce = nonce;
        this.hash = hash;
        this.markleRoot = markleRoot;
        this.dificulty = dificulty;
        this.previusBlockHash = previusBlockHash;
        this.transactions =(transactions != null)? transactions : new ArrayList<>();

    }

    public static Block creatBlock(int index, long nonce, String hash, String markleRoot, int dificulty, String previusBlockHash, List<String> transactions){
        return new Block(index, nonce, hash,markleRoot,dificulty, previusBlockHash, transactions);
    }

    public String calculatehash(){
        String blockString = index + String.valueOf(timeStamp) + data.toString() + nonce + markleRoot + dificulty + previusBlockHash + transactions.toString();

        return sha256(blockString);
    }

    private String sha256(String input){
        try{

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[]  hashByte = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashByte){
                hexString.append(String.format("%02x", b) );
            }
            return hexString.toString();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void addTrasaction(String transaction){
        transactions.add(transaction);
    }

    public List<String> getTransactions(){
        return transactions;
    }

    public void mineBlock(int dificulty){
        this.dificulty = dificulty;
        String target = "0".repeat(dificulty);
        long startTime = System.currentTimeMillis();
        int count = 1;

        while (true){
            String hashCandidate = calculatehash();

            if (hashCandidate.startsWith(target)){
                this.hash = hashCandidate;

                long endTime = System.currentTimeMillis();

                System.out.println("Bloco minerado! Hash: " + hash);

                System.out.println("Tempo em minutos: "+(endTime- startTime)/60000.0);

                break;
            }else{
                nonce++;
                System.out.print(count + "\r");
                count++;
            }
        }

    }

    public Map<String, Object> toMap() {
        Map<String, Object> blockMap = new HashMap<>();

        blockMap.put("index", index);
        blockMap.put("timeStamp", timeStamp);
        blockMap.put("data", data);
        blockMap.put("nonce", nonce);
        blockMap.put("hash", hash);
        blockMap.put("dificulty", dificulty);
        blockMap.put("previousBlockHash", previusBlockHash);
        blockMap.put("merkleRoot", markleRoot);
        blockMap.put("transactions", transactions);

        return blockMap;
    }

}
