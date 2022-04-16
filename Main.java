import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(new File(args[0]));
        Hashtable<Integer, Node> allNodes = new Hashtable<>();
        int lineIndex = 1;
        int nodeIndex = 1;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] lst = line.split(" ");
            for (int i = 0;i<lst.length;i++) {
                Node temp = new Node(Integer.valueOf(lst[i]),lineIndex);
                if (in.hasNextLine()) {
                    temp.indexOfNeigbours.add(nodeIndex+lineIndex);
                    temp.indexOfNeigbours.add(nodeIndex+lineIndex+1);
                }
                allNodes.put(nodeIndex, temp);
                nodeIndex+=1;
            }
            lineIndex+=1;
        }       
        int maxSum = 0;
        int maxLineIndex=1;
        Queue<Node> queue = new LinkedList<>();
        Node currentNode = allNodes.get(1); 
        if (currentNode.isNotPrime()) {         
            queue.add(currentNode);
            currentNode.maxSum = currentNode.value;
            maxSum = currentNode.maxSum;
        }       
        while(!queue.isEmpty()) {           
            currentNode = queue.poll();         
            for (int i : currentNode.indexOfNeigbours) {                
                if (allNodes.get(i).isNotPrime()&&(currentNode.maxSum+allNodes.get(i).value)>allNodes.get(i).maxSum) {
                    allNodes.get(i).maxSum=currentNode.maxSum+allNodes.get(i).value;
                    
                    if (allNodes.get(i).lineIndex>maxLineIndex||allNodes.get(i).maxSum>maxSum) {
                        maxSum = allNodes.get(i).maxSum;
                        maxLineIndex=allNodes.get(i).lineIndex;
                    }
                    if (!queue.contains(allNodes.get(i))) {
                        queue.add(allNodes.get(i));
                    }
                }
            }
        }System.out.println(maxSum);            
    }
}
 
 
class Node {
    int value;
    int maxSum = 0;
    int lineIndex;
    ArrayList<Integer> indexOfNeigbours = new ArrayList<>();
    Node(int value,int lineIndex){
        this.value= value;
        this.lineIndex=lineIndex;
    }
    public boolean isNotPrime() {
        int divisor = 2;
        if (this.value==0||this.value==1) {
            return true;
        }
        while (divisor <= Math.pow(this.value, 0.5)) {          
            if (this.value%divisor==0) {                
                return true;
            }       
            divisor+=1;
        }
        return false;
    }
}