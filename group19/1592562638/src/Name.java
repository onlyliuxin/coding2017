
public class Name implements Comparable<Name> {
	private String firstName,lastName;
	public Name(String firstName,String lastName){
		this.firstName=firstName;
		this.lastName=lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String toString(){return firstName+" "+lastName;}//重写toString方法
	
	//重写equals跟hashCode方法
	public boolean equals(Name name){
		return (firstName.equals(name.firstName) && lastName.equals(name.lastName));
	}
	public int hashCode(){
		return firstName.hashCode();
	}
	
	//重写compareTo方法
	public int compareTo(Name o){
		int lastCmp=lastName.compareTo(o.lastName);
		return (lastCmp!=0?lastCmp:firstName.compareTo(o.firstName));
	}

}
