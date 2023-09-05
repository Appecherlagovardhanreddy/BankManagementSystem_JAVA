package JDBC;
import java.util.*;
import java.util.stream.Stream;


//  Stream , Lambda Function , Anonymous Class , Optional Class

@FunctionalInterface
interface Inter{
	void show();
}

class A implements Inter{
	public void  show() {
		System.out.println("I am in Show");
	}
}

public class practice {

	public static void main(String[] args) {
		
		A a = new A();
		a.show();
//		Normal call
		
		
//		Anonymous Class
		Inter i = new Inter() {
			public void show() {
				System.out.println("I am in ananymous Class");
			}
		};
		i.show();

		//	Lambda Function	
		
		Inter j = ()->{
			System.out.println("I am in lambda function");
		};
		j.show();
		
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		
		for(int k=0;k<10;k++) {
			a1.add(k);
		}
		
        for(int l:a1) {
        	System.out.println("  _____    ");
        	
        	System.out.println("| n = "+l+" |");
        	
        	System.out.println("  _____    ");
        }
		
        
        a1.forEach(i1->System.out.println(i1));
        
        
//        Stream API
        Stream<Integer> st  =a1.stream();
        
        int count = (int) st.count();
        System.out.println("Total Elements: "+count);
        
//        A stream can be used only once
        
        Stream<Integer> st2  =a1.stream();
        
        
        st2.forEach(n->System.out.print(n));
        
        a1.stream().filter(io-> io>2).map(i9->i9*4).sorted().forEach(n->System.out.println(n));;
        
        String str[]=new String[10];
        
        Optional<String>s = Optional.ofNullable(str[7]);
        
         if(s.isPresent()) {
        	 System.out.println("NOT NULL"+s.get());
         }else {
        	 System.out.println("NULL" );
        	  str[7] = s.orElse(new String("Gunavardhan"));
        	 
         }
     System.out.println(str[7]) ;
	}

}
