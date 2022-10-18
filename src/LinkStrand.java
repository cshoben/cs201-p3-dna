public class LinkStrand implements IDnaStrand {
    
    
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;

    private class Node {
        public String info;
        public Node next;
        public Node(){
            info = "";
            next = null;
        }
        public Node(String dnaBases){
            info = dnaBases;
            next = null;
        }
    }

    public LinkStrand(){
        this("");
    }

    public LinkStrand(String s) {
        initialize(s);
    }


    @Override
    public void initialize(String source) {
        Node newNode = new Node(source);
        myFirst = newNode;
        myLast = newNode;
        myCurrent = myFirst;

        mySize = source.length();
        myIndex = 0;
        myAppends = 0;
        myLocalIndex = 0;
    }





    @Override
    public long size() {
        return mySize;
    }
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }






    @Override
    public IDnaStrand append(String dna) {
        Node newNode = new Node(dna);
        myLast.next = newNode;
        myLast = newNode;
        mySize = mySize + dna.length();
        myAppends += 1;
        
        return this;
    }

    @Override
	public String toString() {
        Node currentNode = myFirst;
        StringBuilder dnaStrand = new StringBuilder();
        while (currentNode != null) {
            dnaStrand.append(currentNode.info);
            currentNode = currentNode.next;
        }
        return dnaStrand.toString();
	}

    @Override
    public IDnaStrand reverse() {

        // Make the first node of the new list
        //
        // make a string builder initialized to the contents of the current node.
        Node currentNode = myFirst;
        StringBuilder firstNodeDna = new StringBuilder(currentNode.info);
        // reverse the content of the stringbuilder 
        firstNodeDna.reverse();
        // place string builder reversed string as info for a new node. 
        LinkStrand newList = new LinkStrand(firstNodeDna.toString());
        currentNode =  currentNode.next;      
        
        while (currentNode != null) {
            // make a string builder initialized to the contents of the current node.
            StringBuilder currentNodeDna = new StringBuilder(currentNode.info);
            // reverse the content of the stringbuilder 
            currentNodeDna.reverse();
            // place string builder reversed string as info for a new node. 
            Node newNode = new Node(currentNodeDna.toString());
            newList.mySize += newNode.info.length();
            newNode.next = newList.myFirst;
            newList.myFirst = newNode;
            currentNode = currentNode.next;
        }
        return newList;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if (index > (mySize - 1) || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds. The total size is " + mySize);
        }
        // if the character I'm looking up is ahead of the current 
        // index then I do not have to start from the beginning of the LinkStrand list. 
        if (myIndex < index) {
            while (myCurrent != null) {
                for (int i = myLocalIndex; i < myCurrent.info.length(); i++) {
                    if (myIndex != index) {
                        myIndex += 1;
                        myLocalIndex += 1;
                    } else {
                        char[] nodeChars = myCurrent.info.toCharArray();
                        return nodeChars[myLocalIndex];
                    }
                }
                myCurrent = myCurrent.next;
                myLocalIndex = 0;
            }
        } else { // start at the beginning of the linked list to find the character
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
            while (myCurrent != null){
                for (int i = 0; i < myCurrent.info.length(); i++) {
                    if (myIndex != index) {
                        myIndex += 1;
                        myLocalIndex += 1;
                    } else {
                        char[] nodeChars = myCurrent.info.toCharArray();
                        return nodeChars[myLocalIndex];
                    }
                }
                myCurrent = myCurrent.next;
                myLocalIndex = 0;
            }
        }
        return 0;
    }
    
}
