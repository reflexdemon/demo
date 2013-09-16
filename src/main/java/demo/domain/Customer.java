package demo.domain;

import java.io.Serializable;

import org.codehaus.jettison.json.JSONStringer;

import com.sun.xml.bind.v2.model.core.ID;

/**
 * 
 * @author Venkateswara
 * 
 */
public class Customer implements Serializable {

	/**
	 * Autogenerated
	 */
	private static final long serialVersionUID = 4195183555414925619L;

	/** The Id. */
	private String Id;

	/** The name. */
	private String name;

	/** The age. */
	private String age;

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("(Id:").append(Id).append(")");
		builder.append("(name:").append(name).append(")");
		builder.append("(age:").append(age).append(")");
		builder.append("}");
		return builder.toString();
	}
}
