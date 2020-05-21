import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator{
	Random r ;
	public RandomGenerator() {
	 r = new Random();
	}
	public int getRandom(int m,int n) {
		ArrayList<Integer> num = new ArrayList<Integer>();
		for(int i=m;i<=n;i++){
			if(i!=0)
			num.add(i);
		}
		int num1 = r.nextInt(n-m);
		return num.get(num1);
	}
	public int getRandom2(int a,int b) {
		int num2 = r.nextInt(b-a+1)+a;
		return num2;
	}
	
	
}
