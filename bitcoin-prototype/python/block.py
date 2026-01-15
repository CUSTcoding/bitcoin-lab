from datetime import datetime
import hashlib
import time
import json

class Block:

    def __init__(self, index, nonce, hash, merkle_root,difficulty,  previous_blockhash,transaction=None ):

        self.index = index
        self.time_stamp = datetime.now().timestamp()
        self.data = datetime.now()
        self.nonce = nonce
        self.hash = hash
        self.merkle_root = merkle_root
        self.difficulty = difficulty
        self.previous_blockhash = previous_blockhash
        self.transaction = transaction if transaction else []


    def create_block(self,index, nonce, hash, merkle_root,difficulty,  previous_blockhash,transaction ):

        return Block(index, nonce, hash, merkle_root, difficulty, previous_blockhash, transaction)

    def calculate_hash(self):
        block_string = f"{self.index}{self.time_stamp}{self.data}{self.nonce}{self.hash}{self.merkle_root}{self.difficulty}{self.previous_blockhash}{self.transaction}"
        return hashlib.sha256(block_string.encode()).hexdigest()

    def addTransaction(self, transaction):
        self.transaction.append(transaction)

    def getTransactions(self):
        return self.transaction

    def mine_block(self,difficulty):
        self.difficulty = difficulty
        target = '0' * difficulty

        start_time = time.time()

        count = 1
        while True:
            hash_candidate = self.calculate_hash()
            if hash_candidate[:difficulty] == target:
                self.hash = hash_candidate
                end_time = time.time()
                print(f"Bloco minerado! Hash: {self.hash} \n")
                print(f"Tempo de mineração: {end_time - start_time:.4f} segundos \n")
                print(f"Tempo de mineração: {(end_time - start_time) / 60:.2f} minutos \n")
                break
            else:
                self.nonce +=1
                print(f"{count}",end="\r")
                count +=1
  
    def to_dict(self):
        return {
            "index": self.index,
            "timestamp": self.time_stamp,
            "date": self.data.isoformat(),
            "nonce": self.nonce,
            "difficulty": self.difficulty,
            "hash": self.hash,
            "previous_blockhash": self.previous_blockhash,
            "merkle_root": self.merkle_root,
            "transactions": self.transaction
        }


'''

'''
#block1 = Block(1, 0, '', 'merkle_root_example', 4, '0000', ['transação1', 'transação2'])

'''

'''
#block1.mine_block(6)

'''

'''
#print(json.dumps(block1.to_dict(), indent=4))