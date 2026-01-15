from block import Block
import json

class Blockchain:
    def __init__(self):
        self.chain = []
    
    def getchain(self):
        return self.chain
    
    def addblock(self, block):
        self.chain.append(block)


bc = Blockchain()
block1 = Block(1, 0, '', 'merkle_root_example', 4, '0000')
block1.addTransaction('transação1')
block1.addTransaction('transação2')
block1.mine_block(12)
bc.addblock(block1)
bc.getchain()
print(json.dumps([b.to_dict() for b in bc.getchain()], indent=4))
