/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ThanhTung
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int salary;
    
    public Employee(int id, String firstName, String lastName, int salary){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }
    
    public int getId(){
        return id;
    }   
    public void setId(int id){
        this.id = id;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName ){
        this.firstName = firstName;
    }
     
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public String getName(){
        return firstName+" "+lastName;
    }
    
    public int getSalary(){
        return salary;
    }
    
    public void setSalary(int salary){
        this.salary = salary;
    }
    
    public int getAnnualSalary(){
        return salary*12;
    }
    
    public int raiseSalary(int percent){
        if(percent == 0) return salary;
        salary = salary + (int) (salary * percent/100.0);
       return salary;
    }

    @Override
    public String toString(){
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
