package others;
import lessones01.LinkedListWithFloat;
public class Others{
	public static class PrimeGetter{
		private static LinkedListWithFloat<Integer> primes = new LinkedListWithFloat<Integer>();
		public static int get(int arg_num){
			for(int i = primes.size()-1;i<arg_num;i++){add();}
			return primes.get(arg_num);
		}
		private static void add(){
			int size = primes.size();
			if(size == 0){
				primes.add(2);
			}else if(size == 1){
				primes.add(3);
			}else{
				for(int x = primes.get(size-1)+2;;x+=2){
					int max = Math.sqrt(x);
					boolean isPrime = true;
					primes.get(0);
					while(primes.floatNext()<max){
						if(x%primes.floatValue == 0){
							isPrime = false;
							break;
						}
					}
					if(isPrime){
						primes.add(x);
						return;
					}
				}
			}
		}
		public static boolean isPrime(int arg_num){
			for(int i = 0;arg_num<primes.get(i);i++){}
			return arg_num == prime.get(i);
		}
	}
}