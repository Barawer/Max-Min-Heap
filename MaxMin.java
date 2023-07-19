import java.lang.Math;

public class MaxMin {

    int[] heap;
    int heapSize;
    //find the level and check if its even or odd
    private static boolean isEvenLevel(int index){
        int level=(int)Math.floor(Math.log(index+1)/Math.log(2));
        return level%2==0;
    }
    //swap between two elements in the heap
    private void swap(int i, int swap){
        int temp=heap[i];
        heap[i]=heap[swap];
        heap[swap]=temp;
    }
    //finds the smallest or largest descendant, according to the level.
    private int findDescent(int i,int flag){
        int leftChild=2*i+1;
        int rightChild=2*i+2;
        int leftLeftGrandchild=2*i+3;
        int leftRightGrandchild=2*i+4;
        int rightLeftGrandchild=2*i+5;
        int rightRightGrandchild=2*i+6;
        int swapper=i;

        if(flag==0){
            if(isEvenLevel(i)){
                if(heap[leftChild]>heap[swapper]){
                    swapper=leftChild;
                }
                else if(rightChild<this.heapSize && heap[rightChild]>heap[swapper] ){
                    swapper=rightChild;
                }
                else if(leftLeftGrandchild<this.heapSize && heap[leftLeftGrandchild]>heap[swapper]){
                    swapper=leftLeftGrandchild;
                }
                else if(leftRightGrandchild<this.heapSize && heap[leftRightGrandchild]>heap[swapper]){
                    swapper=leftRightGrandchild;
                }
                else if(rightLeftGrandchild<this.heapSize && heap[rightLeftGrandchild]>heap[swapper]){
                    swapper=rightLeftGrandchild;
                }
                else if(rightRightGrandchild<this.heapSize && heap[rightRightGrandchild]>heap[swapper]){
                    swapper=rightRightGrandchild;
                }
            }
        }
        else{
                if(heap[leftChild]<heap[swapper]){
                    swapper=leftChild;
                }
                else if(rightChild<this.heapSize && heap[rightChild]<heap[swapper]){
                    swapper=rightChild;
                }
                else if(leftLeftGrandchild<this.heapSize && heap[leftLeftGrandchild]<heap[swapper]){
                    swapper=leftLeftGrandchild;
                }
                else if(leftRightGrandchild<this.heapSize && heap[leftRightGrandchild]<heap[swapper]){
                    swapper=leftRightGrandchild;
                }
                else if(rightLeftGrandchild<this.heapSize && heap[rightLeftGrandchild]<heap[swapper]){
                    swapper=rightLeftGrandchild;
                }
                else if(rightRightGrandchild<this.heapSize && heap[rightRightGrandchild]<heap[swapper]){
                    swapper=rightRightGrandchild;
                }
        }
        return swapper;
    }
    public void heapify(int i){
        //get the indices of the children and the index of the node to swap with
        int leftChild=2*i+1;
        int rightChild=2*i+2;
        int swapper=i;
        //check if the node has children. if not, end the method.
        if(leftChild>=this.heapSize){
            return;
        }
        //check if the level is even or odd
        if(isEvenLevel(i)){
            swapper=findDescent(i,0);
        }
        else{
            swapper=findDescent(i,1);
        }
        //if we found a smaller or larger node, swap them and re-run heapify.
        if(swapper!=i){
            swap(i,swapper);
            heapify(swapper);
            heapify(i);
        }
    }
    public void buildHeap(int[] A){
        heapSize=A.length;
        heap=new int[heapSize];
        System.arraycopy(A,0,heap,0,heapSize);
        for(int i=(int)Math.floor(heapSize/2);i>=0;i--){
            heapify(i);
        }
    }

    public int heapExtractMax(){
        if(heapSize==0){
            System.out.println(Integer.MIN_VALUE+ " Since the heap is empty");
            return Integer.MIN_VALUE;
        }
        int max=heap[0];
        heap[0]=heap[heapSize-1];
        heapSize--;
        int[] tempHeap=new int[heapSize];
        System.arraycopy(heap,0,tempHeap,0,heapSize);
        heap=tempHeap;
        heapify(0);
        return max;
    }

    public int heapExtractMin(){
        int min;
        int minI;
        if(heapSize==0){
            System.out.println(Integer.MAX_VALUE + " Since the heap is empty!");
            return Integer.MAX_VALUE;
        }
        //only the left child exists
        else if(heapSize==2){
            min=heap[1];
            minI=1;
        }
        //only the root exists
        else if(heapSize==1){
            min=heap[0];
            minI=0;
        }
        else {
            min = Math.min(heap[1], heap[2]);
            if (min == heap[1]) {
                heap[1] = heap[heapSize - 1];
                minI = 1;
            } else {
                heap[2] = heap[heapSize - 1];
                minI = 2;
            }
        }
        heapSize--;
        int[] tempHeap=new int[heapSize];
        System.arraycopy(heap,0,tempHeap,0,heapSize);
        heap=tempHeap;
        heapify(minI);
        return min;
    }

    public void heapInsert(int key){
        int[] tempHeap=new int[heapSize+1];
        System.arraycopy(heap,0,tempHeap,0,heapSize);
        heapSize++;
        heap=tempHeap;
        int i=heapSize-1;
        heap[i]=key;
        while(i>0){
            int parent=(i-1)/2;
            if(isEvenLevel(i)){
                if(heap[i]>heap[parent]){
                    swap(i,parent);
                }
            }
            else{
                if(heap[i]<heap[parent]){
                    swap(i,parent);
                }
            }
            i=parent;
        }
        heapify(0);
    }

    public void heapDelete(int key){
        int keyLoc=Integer.MAX_VALUE;
        for(int i=0;i<heapSize;i++){
            if(heap[i]==key){
                keyLoc=i;
            }
        }

        if(keyLoc==Integer.MAX_VALUE){
            System.out.println("The key does not exist in the heap!");
            return;
        }
        heap[keyLoc]=heap[heapSize-1];
        heapSize--;
        int[] tempHeap=new int[heapSize];
        System.arraycopy(heap,0,tempHeap,0,heapSize);
        heap=tempHeap;
        heapify(keyLoc);
    }

    public void printHeap(){
        for (int i=0;i<heapSize;i++){
            System.out.print(heap[i]);
            System.out.print('\t');
        }
        System.out.print('\n');
    }
}
