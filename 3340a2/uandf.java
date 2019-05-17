//Nicholas Gardi - 250868721

//code is largely adapted from textbook pseudocode for efficient union-find algorithm

public class uandf {

    private int final_flag = 0;
    private node list[];
    
  //node class to be used in the uandf class
    class node {
       int element;
       int rank;
       node parent;
        public node() {      
            rank = 0;
        }
       public node(int i) {       
           element = i;
           rank = 0;
       }
   }

    
    //constructor
    public uandf(int n){
        list = new node[n];
    }

    
    //creates a new set whose only member (and thus representative) is i.
    public void make_set(int i){
        if (final_flag==0) {
            node new_node = new node(i);
            new_node.parent = new_node;
            list[i]= new_node;
        }
    }


    //unites the dynamic sets that contains i and j, respectively, into a new
    //set that is the union of these two sets.
    public void union_sets(int i, int j){
        if (final_flag == 0) {
            node a = find_set(i);
            node b = find_set(j);
            if (a == b){
                return;
            }
            if (a.rank > b.rank) {
                b.parent = a;
            }
            else{
                a.parent = b;
                if (a.rank == b.rank) {
                    b.rank = b.rank +1;
                }
            }
        }
    }
    

    //unites the dynamic sets that contains i and j, respectively, into a new
    //set that is the union of these two sets.
    public node find_set(int i){
        node temp = new node(); //maybe dont need to init
        for (int j=0;j<list.length;j++){
            if (list[j] == null){
            }
            else{
                if (list[j].element == i){
                    temp = list[j];
                }
            }
        }
        if (temp.element != temp.parent.element){
            temp.parent = find_set(temp.parent.element);
        }
        return temp.parent;
    }

    
    //returns the representative of the set containing i.
    public int find_set_element(int i){
       int set_element = find_set(i).element;
       return set_element;
    }


    //returns the total number of current sets, finalizes the current sets (make set()
    //and union sets() will have no effect after this operation), and resets the representatives
    //of the sets so that integers from 1 to final sets() will be used as representatives.
    public int final_sets(){
        final_flag = 1;
        int number_of_sets = 0;
        for (int j=0;j<list.length;j++){
            if (list[j] == find_set(list[j].element)){
                number_of_sets+=1;
            }
        }
        return number_of_sets;
    }


}

