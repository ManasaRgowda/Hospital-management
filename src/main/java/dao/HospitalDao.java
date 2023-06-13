package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Doctor;
import dto.Patient;
import dto.Staff;

public class HospitalDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
	EntityManager manager=factory.createEntityManager();
	EntityTransaction transaction= manager.getTransaction();
	 
	public void saveStaff(Staff staff1) {		
		transaction.begin();
		manager.persist(staff1);
		transaction.commit();
	}

	public void saveDoctor(Doctor dtr) {
		transaction.begin();
		manager.persist(dtr);
		transaction.commit();
	}
	//for mobile and email validation to store the data without duplicates
	public Staff fetchStaff(long mobile){
	   List<Staff> list=manager.createQuery("select x from Staff x where mobile=?1").setParameter(1,mobile).getResultList();
		  if(list.isEmpty())
		     {
			 return null;
		     }
		 else
		  {
			 return list.get(0);
   	        }
	}
    public Staff fetchStaff(String email){
	  List<Staff> list=manager.createNativeQuery("select * from Staff where email=?1",Staff.class).setParameter(1,email).getResultList();
			if(list.isEmpty())      //createNativeQueryis used to write sql query
			 {
				 return null;
			 }
			 else
			  {
				 return list.get(0);
	     	 }
	}

	public Doctor fetchDoctor(long mobile) {
		 List<Doctor> list=manager.createQuery("select x from Doctor x where mobile=?1").setParameter(1,mobile).getResultList();
		  if(list.isEmpty())
		     {
			 return null;
		     }
		 else
		  {
			 return list.get(0);
  	        }
	}
	public Doctor fetchDoctor(String email) {
		 List<Doctor> list=manager.createQuery("select x from Doctor x where email=?1").setParameter(1,email).getResultList();
		  if(list.isEmpty())
		     {
			 return null;
		     }
		 else
		  {
			 return list.get(0);
 	        }
	}
	
	//fetch the data by using id to login and 
	public Staff fetchStaff(int id)
	{
		return manager.find(Staff.class, id);
	}
	public Doctor fetchDoctor(int id)
	{
		return manager.find(Doctor.class, id);
	}

	public void updateStaff(Staff staff) {
		transaction.begin();
		manager.merge(staff);
		transaction.commit();		
	}

	public void updatedoctor(Doctor doctor) {
		transaction.begin();
		manager.merge(doctor);
		transaction.commit();		
	}
	
	//to give admin approval
	public List<Staff> fetchAllStaff()
	{
		return manager.createQuery("select x from Staff x").getResultList();
	}
	public List<Doctor> fetchAllDoctor()
	{
		return manager.createQuery("select x from Doctor x").getResultList();
	}

	public void savePatient(Patient patient) {
		transaction.begin();
		manager.persist(patient);
		transaction.commit();
	}
	
	//patient validate

	public Patient fetchpatient(long mobile) {
		 List<Patient> list=manager.createQuery("select x from Doctor x where mobile=?1").setParameter(1,mobile).getResultList();
		  if(list.isEmpty())
		     {
			 return null;
		     }
		 else
		  {
			 return list.get(0);
	        }
	}

	public List<Patient> fetchAllPatient() {
		return manager.createQuery("select x from Patient x").getResultList();
	}

	//to edit/update
		public Patient fetch1(int id){
		return manager.find(Patient.class, id);
		}

		public void update(Patient dto) {
			transaction.begin();
			manager.merge(dto);
			transaction.commit();		
		}
	
}
