public class LinkStrand implements IDnaStrand {
    
    
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int MyLocalIndex;

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
        MyLocalIndex = 0;
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
    public IDnaStrand reverse() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
