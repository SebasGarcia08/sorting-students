package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Course {
	private Student[] students;
	
	//----------------------------------------------------------------------------------
	// CONSTRUCTOR
	//----------------------------------------------------------------------------------
	public Course(Student[] sts) {
		students = sts;
	}
	
	//----------------------------------------------------------------------------------
	// GETTER
	//----------------------------------------------------------------------------------
	public Student[] getStudents() {
		return students;
	}
	
	//----------------------------------------------------------------------------------
	// SELECTION SORTING BY LASTNAME
	//----------------------------------------------------------------------------------	
	public void sortByLastname() {
		for (int I = 0; I < students.length-1; I++) {
			String minLastName = students[I].getLastname();
			int minPosition = I;
			
			for (int J = I+1; J < students.length; J++) {
				String currentLastName = students[J].getLastname();
				if(currentLastName.compareTo(minLastName) < 0) {
					minLastName = currentLastName;
					minPosition = J;
				}
			}
			
			Student temp = students[minPosition];
			students[minPosition] = students[I];
			students[I] = temp;
		}
	}
	
	//----------------------------------------------------------------------------------
	// BORING SORTING BY CODE ._.
	//----------------------------------------------------------------------------------
	public void sortByCode() {
		Arrays.sort(students);
	}

	//----------------------------------------------------------------------------------
	// BORING SORTING BY NAME ._.
	//----------------------------------------------------------------------------------
	public void sortByName() {
		Comparator<Student> studentComparator = new StudentNameComparator();
		
		Arrays.sort(students,studentComparator);
	}
	
	//----------------------------------------------------------------------------------
	// BORING SORTING BY LAST NAME ._.
	//----------------------------------------------------------------------------------
	public void sortByFullName() {
		Arrays.sort(students, new StudentFullNameComparator());
	}
	
/*

							 ▄▀▀█▄   ▄▀▀▄ ▀▄  ▄▀▀▀▀▄   ▄▀▀▄ ▀▄  ▄▀▀▄ ▀▀▄  ▄▀▀▄ ▄▀▄  ▄▀▀▀▀▄   ▄▀▀▄ ▄▀▀▄  ▄▀▀▀▀▄ 
							▐ ▄▀ ▀▄ █  █ █ █ █      █ █  █ █ █ █   ▀▄ ▄▀ █  █ ▀  █ █      █ █   █    █ █ █   ▐ 
							  █▄▄▄█ ▐  █  ▀█ █      █ ▐  █  ▀█ ▐     █   ▐  █    █ █      █ ▐  █    █     ▀▄   
							 ▄▀   █   █   █  ▀▄    ▄▀   █   █        █     █    █  ▀▄    ▄▀   █    █   ▀▄   █  
							█   ▄▀  ▄▀   █     ▀▀▀▀   ▄▀   █       ▄▀    ▄▀   ▄▀     ▀▀▀▀      ▀▄▄▄▄▀   █▀▀▀   
							▐   ▐   █    ▐            █    ▐       █     █    █                         ▐      
							        ▐                 ▐            ▐     ▐    ▐                                
							
							
									 ▄▀▄▄▄▄   ▄▀▀▀▀▄      ▄▀▀█▄   ▄▀▀▀▀▄  ▄▀▀▀▀▄  ▄▀▀█▄▄▄▄  ▄▀▀▀▀▄ 
									█ █    ▌ █    █      ▐ ▄▀ ▀▄ █ █   ▐ █ █   ▐ ▐  ▄▀   ▐ █ █   ▐ 
									▐ █      ▐    █        █▄▄▄█    ▀▄      ▀▄     █▄▄▄▄▄     ▀▄   
									  █          █        ▄▀   █ ▀▄   █  ▀▄   █    █    ▌  ▀▄   █  
									 ▄▀▄▄▄▄▀   ▄▀▄▄▄▄▄▄▀ █   ▄▀   █▀▀▀    █▀▀▀    ▄▀▄▄▄▄    █▀▀▀   
									█     ▐    █         ▐   ▐    ▐       ▐       █    ▐    ▐      
									▐          ▐                                  ▐                

*/

	/*
	  Anonymous classes allows us to avoid the annoying use of external classes. 
	*/
	public void sortByNameAnonymous() {
		/* 
		 Seyerman's code, but on steroids:
		*/
		Arrays.sort(students, new Comparator<Student>() {
			
			@Override
			public int compare(Student student1, Student student2) {
				int comparation;
				String name1 = student1.getName();
				String name2 = student2.getName();
				
				if(name1.compareTo(name2)<0) {
					comparation = -1;
				}else if(name1.compareTo(name2)>0) {
					comparation = 1;
				}else {
					comparation = 0;
				}
					
				return comparation;
			}
		});
	}
	
	public void sortByFullNameAnonymous() {
		/**
		 Does the same as sortByFullName, but this code makes use of some declarative paradigm.
		 */
		Arrays.sort(students, new Comparator<Student>() {
			
			@Override
			public int compare(Student s1, Student s2) {				
				String[] ns = Arrays.asList(s1, s2).stream()
									   .map(Student::getName)
									   .toArray(String[]::new);
				
				String[] ls = Arrays.asList(s1, s2).stream()
						   				.map(Student::getLastname)
						   				.toArray(String[]::new);
				
				if( ls[0].compareTo(ls[1]) < 0 ) return -1;
				else if( ls[0].compareTo(ls[1]) > 0 ) return 1;
				else {
					if (ns[0].compareTo(ns[1]) < 0) return -1;
					else if (ns[0].compareTo(ns[1]) > 0) return 1;
					else return 0;
				}
			}
		});
	}
	
/*
									 
									 ▄▀▀▀▀▄      ▄▀▀█▄   ▄▀▀▄ ▄▀▄  ▄▀▀█▄▄   ▄▀▀█▄▄   ▄▀▀█▄  
									█    █      ▐ ▄▀ ▀▄ █  █ ▀  █ ▐ ▄▀   █ █ ▄▀   █ ▐ ▄▀ ▀▄ 
									▐    █        █▄▄▄█ ▐  █    █   █▄▄▄▀  ▐ █    █   █▄▄▄█ 
									    █        ▄▀   █   █    █    █   █    █    █  ▄▀   █ 
									  ▄▀▄▄▄▄▄▄▀ █   ▄▀  ▄▀   ▄▀    ▄▀▄▄▄▀   ▄▀▄▄▄▄▀ █   ▄▀  
									  █         ▐   ▐   █    █    █    ▐   █     ▐  ▐   ▐   
									  ▐                 ▐    ▐    ▐        ▐                

				 ▄▀▀█▄▄▄▄  ▄▀▀▄  ▄▀▄  ▄▀▀▄▀▀▀▄  ▄▀▀▄▀▀▀▄  ▄▀▀█▄▄▄▄  ▄▀▀▀▀▄  ▄▀▀▀▀▄  ▄▀▀█▀▄   ▄▀▀▀▀▄   ▄▀▀▄ ▀▄  ▄▀▀▀▀▄ 
				▐  ▄▀   ▐ █    █   █ █   █   █ █   █   █ ▐  ▄▀   ▐ █ █   ▐ █ █   ▐ █   █  █ █      █ █  █ █ █ █ █   ▐ 
				  █▄▄▄▄▄  ▐     ▀▄▀  ▐  █▀▀▀▀  ▐  █▀▀█▀    █▄▄▄▄▄     ▀▄      ▀▄   ▐   █  ▐ █      █ ▐  █  ▀█    ▀▄   
				  █    ▌       ▄▀ █     █       ▄▀    █    █    ▌  ▀▄   █  ▀▄   █      █    ▀▄    ▄▀   █   █  ▀▄   █  
				 ▄▀▄▄▄▄       █  ▄▀   ▄▀       █     █    ▄▀▄▄▄▄    █▀▀▀    █▀▀▀    ▄▀▀▀▀▀▄   ▀▀▀▀   ▄▀   █    █▀▀▀   
				 █    ▐     ▄▀  ▄▀   █         ▐     ▐    █    ▐    ▐       ▐      █       █         █    ▐    ▐      
				 ▐         █    ▐    ▐                    ▐                        ▐       ▐         ▐                
 */

	public void sortByNameLambda() {
		Arrays.asList(students).sort((s1, s2) -> s1.compareTo(s2));
	}
	
	public void sortByFullnameLambda() {
		Arrays.asList(students).sort((s1, s2) -> {
				String[] ns = Arrays.asList(s1, s2).stream()
									   .map(Student::getName)
									   .toArray(String[]::new);
				
				String[] ls = Arrays.asList(s1, s2).stream()
						   				.map(Student::getLastname)
						   				.toArray(String[]::new);
				
				if( ls[0].compareTo(ls[1]) < 0 ) return -1;
				else if( ls[0].compareTo(ls[1]) > 0 ) return 1;
				else {
					if (ns[0].compareTo(ns[1]) < 0) return -1;
					else if (ns[0].compareTo(ns[1]) > 0) return 1;
					else return 0;
				}
		});
	}
}
