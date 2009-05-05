package org.zkbase.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;

@Entity
@Table(name="zkb_role")
public class Role implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = -3415699490636341355L;
	
	private Long id;
    private String name;
    private String description;
    
    @Id
	@TableGenerator(
			name="roleGen",
			table="ID_GEN",
			pkColumnName="GEN_KEY",
			valueColumnName="GEN_VALUE",
			pkColumnValue="ROLE_ID",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="roleGen")	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	@Transient
	public String getAuthority() {
		return this.getName();
	}
	
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        final Role role = (Role) o;
        
        if (role.name == null || name == null)
        	return false;
        
        return name.equals(role.name);
    }
    
	@Override
    public int compareTo(Object o) {
        return (equals(o) ? 0 : -1);
    }    
}
