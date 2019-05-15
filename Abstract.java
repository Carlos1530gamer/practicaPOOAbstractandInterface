
interface PrintMethods {
	default void print(Object argument){
		System.out.print(argument);
	}

	default void println(Object argument){
		System.out.println(argument);
	}
}

interface Study {
	void study();
}

abstract class Creature implements PrintMethods{
	String name;
	String genere;


	abstract void eat();
	abstract void sleep();
}


class Human extends Creature{

	public Human(String name,String genere){
		this.name = name;
		this.genere = genere;
	}

	void eat(){
		println(this.name + " is eating");
	}

	void sleep(){
		println(" is sleeping in a beed");
	}
}

class Animal extends Creature{
	public Animal(String name, String genere){
		this.name = name;
		this.genere = genere;
	}
	void eat(){
		println(this.name + " is eating in the floor");
	}

	void sleep(){
		println(" is sleeping in the floor");
	}
}

class Student extends Human implements Study{

	public Student(String name, String genere){
		super(name, genere);
	}

	@Override
	void eat(){
		super.eat();
		println(this.name + " eat maruchan");
	}

	@Override
	void sleep(){
		println(" the student no sleep");
	}

	public void study(){
		println(" the student ever study");
	}
}

class Teacher extends Human implements Study{

	public Teacher(String name, String genere){
		super(name,genere);
	}

	@Override
	void eat(){
		super.eat();
		println(this.name + " eat a apple");
	}

	@Override
	void sleep(){
		println(this.name + " prepare a next class");
	}

	public void study(){
		println(this.name + " study for the next class");
	}
}

class Cat extends Animal{
	public Cat(String name, String genere){
		super(name,genere);
	}
	void eat(){
		println(this.name + " wake up");
	}

	void sleep(){
		println(this.name + " finish eat");
	}
}

public class Abstract{
	public static void main(String[] args) {
		Student alumno = new Student("Carlos", "Male");
		Teacher teacher = new Teacher("Mai", "Female");
		Human human = new Human("human", "???");

		Cat cat = new Cat("Sakamoto", "Male");

		alumno.eat();
		human.eat();
		teacher.eat();
		cat.eat();

		alumno.sleep();
		human.eat();
		teacher.sleep();
		cat.sleep();

		//only this objects get a interface
		alumno.study();
		teacher.study();
	}
}
