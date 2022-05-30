package com.te.lmsproject.entity.mentor;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.te.lmsproject.entity.admin.Mentor;
import com.te.lmsproject.entity.util.Technologies;

import lombok.Data;

@Entity
@Data
public class Mock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int mockNo;
	
	@ManyToOne
	private Technologies techology;
	
	@ManyToMany
	private List<Mentor> mentor;
	
	private LocalDateTime date;
	
	@OneToOne(cascade = CascadeType.ALL)
	private MockRatings mockRatings;
	
	
	}



