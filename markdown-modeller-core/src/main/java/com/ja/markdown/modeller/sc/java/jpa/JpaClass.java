package com.ja.markdown.modeller.sc.java.jpa;

import com.ja.markdown.modeller.sc.java.model.JavaClass;

public enum JpaClass {

	Entity, EntityManager, Column, GeneratedValue, GenerationType, Id, JoinColumn, ManyToMany, ManyToOne, NamedQuery, NamedQueries, OneToMany, OrderBy, PersistenceContext, Temporal, TemporalType;

	public JavaClass instance() {
		return new JavaClass("javax.persistence." + toString());
	}
}
